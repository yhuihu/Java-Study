package main

import (
	"bufio"
	"bytes"
	"example.com/golang/colly/util"
	"fmt"
	"github.com/gocolly/colly"
	"github.com/gocolly/colly/extensions"
	"github.com/spf13/viper"
	"io"
	"io/ioutil"
	"log"
	"net/url"
	"os"
	"path"
	"strconv"
	"strings"
)

func main() {

	viper.SetConfigFile("./config/application.yml")
	err := viper.ReadInConfig()
	if err != nil {
		fmt.Println("读取配置文件错误！" + err.Error())
	}

	c := colly.NewCollector(func(collector *colly.Collector) {
		collector.Async = true
		extensions.RandomUserAgent(collector)
	})

	targetUrl := viper.GetString(constant.SPIDE_URL)
	keyword := viper.GetString(constant.SPIDE_KEYWORD)
	pageSize := viper.GetInt(constant.SPIDE_PAGE_SIZE)
	imgPath := viper.GetString(constant.SPIDE_SAVE_PATH)

	pathMap := make(map[string]string)

	dir := IsDir(imgPath)

	if !dir {
		createDir(imgPath)
	}

	c.OnHTML("#videos", func(e *colly.HTMLElement) {
		e.ForEach("a", func(_ int, elem *colly.HTMLElement) {
			text := elem.ChildText(".uid")
			fmt.Println("开始解析：" + text)
			tmpPath := imgPath + text
			os.Mkdir(tmpPath, 0777)
			attr := elem.Attr("href")
			pathMap[strings.Split(attr, "/")[len(strings.Split(attr, "/"))-1]] = tmpPath
			c.Visit(e.Request.AbsoluteURL(attr))
		})
	})

	c.OnHTML(".sub-column", func(e *colly.HTMLElement) {
		e.ForEach("button[class='button is-info is-small copy-to-clipboard']", func(_ int, elem *colly.HTMLElement) {
			attr := elem.Attr("data-clipboard-text")
			fileName := strings.Split(e.Request.URL.String(), "/")[len(strings.Split(e.Request.URL.String(), "/"))-1] + ".txt"
			file := IsFile(fileName)
			var f *os.File
			if !file {
				f, _ = os.Create(pathMap[strings.Split(fileName, ".")[0]] + "\\" + fileName)
			} else {
				f, _ = os.OpenFile(pathMap[strings.Split(fileName, ".")[0]]+"\\"+fileName, os.O_CREATE|os.O_WRONLY|os.O_APPEND, 0666)
			}
			defer f.Close()
			writer := bufio.NewWriter(f)
			writer.WriteString(attr + "\n")
			writer.Flush()
		})
	})

	c.OnHTML(".preview-images", func(e *colly.HTMLElement) {
		e.ForEach("a", func(_ int, elem *colly.HTMLElement) {
			attr := elem.Attr("href")
			imgUrl := e.Request.AbsoluteURL(attr)
			if imgUrl != "" {
				err := c.Visit(imgUrl)
				if err != nil {
					fmt.Println(err.Error())
				}
			}
		})
	})

	c.OnRequest(func(r *colly.Request) {
		fmt.Println("Visiting", r.URL)
		r.Headers.Add("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		r.Headers.Add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36 Edg/93.0.961.44")
	})

	c.OnResponse(func(r *colly.Response) {
		body := r.Body
		if !strings.HasSuffix(r.Request.URL.String(), "jpg") {
			return
		}
		go func() {
			fileName := strings.Split(r.Request.URL.String(), "/")[len(strings.Split(r.Request.URL.String(), "/"))-1]
			_, err := url.QueryUnescape(fileName) // 对url格式进行转换，要不然看不懂
			f, err := os.Create(pathMap[strings.Split(fileName, "_")[0]] + "\\" + fileName)
			if err != nil {
				panic(err)
			}
			io.Copy(f, bytes.NewReader(body))
		}()
	})

	c.OnError(func(response *colly.Response, err error) {
		fmt.Println(err.Error())
	})

	unescape := url.QueryEscape(keyword)
	for i := 1; i <= pageSize; i++ {
		err2 := c.Visit(targetUrl + "/search?f=all&page=" + strconv.Itoa(i) + "&q=" + unescape + "&sb=1")
		if err2 != nil {
			fmt.Println(err2.Error())
		}
	}
	c.Wait()

	fmt.Println("开始清空空目录！")

	folder, err := findEmptyFolder(imgPath)
	if err != nil {
		fmt.Println(err.Error())
		return
	}
	for _, dir := range folder {
		if err := os.Remove(dir); err != nil {
			fmt.Println("错误:", err.Error())
		} else {
			fmt.Println("删除成功:", dir)
		}
	}
	fmt.Println("数据爬取已完成，请自行关闭窗口。")
	select {}
}

func findEmptyFolder(dirname string) (emptys []string, err error) {

	files, err := ioutil.ReadDir(dirname)
	if err != nil {
		return nil, err
	}
	// 判断底下是否有文件
	if len(files) == 0 {
		return []string{dirname}, nil
	}

	for _, file := range files {
		if file.IsDir() {
			edirs, err := findEmptyFolder(path.Join(dirname, file.Name()))
			if err != nil {
				return nil, err
			}
			if edirs != nil {
				emptys = append(emptys, edirs...)
			}
		}
	}
	return emptys, nil
}

/*
*
判断文件是否存在
*/
func IsDir(fileAddr string) bool {
	s, err := os.Stat(fileAddr)
	if err != nil {
		log.Println(err)
		return false
	}
	return s.IsDir()
}

func IsFile(fileAddr string) bool {
	_, err := os.Stat(fileAddr)
	if err != nil {
		//log.Println(err)
		return false
	}
	return true
}

/*
*
创建文件夹
*/
func createDir(dirName string) bool {
	err := os.MkdirAll(dirName, os.ModePerm)
	if err != nil {
		log.Println(err)
		return false
	}
	return true
}
