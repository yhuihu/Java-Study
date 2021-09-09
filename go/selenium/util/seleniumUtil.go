package util

import (
	"errors"
	"example.com/selenium/config"
	"fmt"
	"github.com/tebeka/selenium"
	"net"
	"os"
)

type Crawler struct {
	ChromeDriver string
	Port         int
	Service      *selenium.Service
	Caps         selenium.Capabilities
}

// NewCrawler 开启驱动服务
func NewCrawler() (*Crawler, error) {
	port, _ := pickUnusedPort()
	crawler := &Crawler{
		//google浏览器驱动
		ChromeDriver: config.VIPER.GetString(config.DRIVER_PATH),
		Port:         port,
		Service:      nil,
	}
	opts := []selenium.ServiceOption{
		selenium.Output(os.Stderr),
	}
	service, err := selenium.NewChromeDriverService(crawler.ChromeDriver, crawler.Port, opts...)
	if nil != err {
		return nil, errors.New("start a chromedriver service failed," + err.Error())
	}
	imagCaps := map[string]interface{}{
		"profile.managed_default_content_settings.images": 2, //不加载图片，提高浏览器响应速度
	}
	caps := selenium.Capabilities{
		"browserName":     "msedge",
		"excludeSwitches": [1]string{"enable-automation"},
		"args": []string{
			"--headless",
			"--start-maximized",
			"--no-sandbox",
			"--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36",
		},
		"prefs": imagCaps,
	}
	//chromeCaps := chrome.Capabilities{
	//	Prefs: imagCaps,
	//	Path:  "",
	//}
	////以上是设置浏览器参数
	//caps.AddChrome(chromeCaps)
	crawler.Service = service
	crawler.Caps = caps
	return crawler, nil
}

// NewRemote 打开窗口
func (c *Crawler) NewRemote() (selenium.WebDriver, error) {
	wB1, err := selenium.NewRemote(c.Caps, fmt.Sprintf("http://localhost:%d/wd/hub", c.Port))
	if err != nil {
		return nil, errors.New("connect to the webDriver failed," + err.Error())
	}
	return wB1, nil
}

// Shutdown 关闭驱动服务
func (c *Crawler) Shutdown() {
	_ = c.Service.Stop()
}

func pickUnusedPort() (int, error) {
	addr, err := net.ResolveTCPAddr("tcp", "127.0.0.1:0")
	if err != nil {
		return 0, err
	}

	l, err := net.ListenTCP("tcp", addr)
	if err != nil {
		return 0, err
	}
	port := l.Addr().(*net.TCPAddr).Port
	if err := l.Close(); err != nil {
		return 0, err
	}
	return port, nil
}
