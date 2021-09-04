package main

import (
	"database/sql"
	"fmt"
	_ "github.com/go-sql-driver/mysql"
	"log"
	"time"
)

var MysqlDb *sql.DB
var MysqlDbErr error

const (
	UserName = "root"
	PassWord = "jsw135799"
	HOST     = "115.29.243.4"
	PORT     = "3306"
	DATABASE = "blog"
	CHARSET  = "utf8"
)

// 初始化链接
func init() {

	dbDSN := fmt.Sprintf("%s:%s@tcp(%s:%s)/%s?charset=%s", UserName, PassWord, HOST, PORT, DATABASE, CHARSET)

	// 打开连接失败
	MysqlDb, MysqlDbErr = sql.Open("mysql", dbDSN)
	//defer MysqlDb.Close();
	if MysqlDbErr != nil {
		log.Println("dbDSN: " + dbDSN)
		panic("数据源配置不正确: " + MysqlDbErr.Error())
	}

	// 最大连接数
	MysqlDb.SetMaxOpenConns(100)
	// 闲置连接数
	MysqlDb.SetMaxIdleConns(20)
	// 最大连接周期
	MysqlDb.SetConnMaxLifetime(100 * time.Second)

	if MysqlDbErr = MysqlDb.Ping(); nil != MysqlDbErr {
		panic("数据库链接失败: " + MysqlDbErr.Error())
	}

}

type Field struct {
	id      string
	title   string
	summary string
}

func main() {
	defer func(MysqlDb *sql.DB) {
		err := MysqlDb.Close()
		if err != nil {
			fmt.Println("关闭mysql连接失败！！！！！！！！！！！！")
		}
	}(MysqlDb)
	// 查询
	fields := search()
	for _, tmpResult := range fields {
		fmt.Println(tmpResult)
	}
	// 插入
	insert()
	// 删除
	delete()
}

func search() []Field {
	query, err := MysqlDb.Query("select id,title,summary from blog")
	if err != nil {
		fmt.Println("查询出现未知错误！！！！！")
	}
	defer func(query *sql.Rows) {
		err := query.Close()
		if err != nil {
			println("出现位置错误。")
		}
	}(query)
	var result []Field
	for query.Next() {
		var tmpTarget Field
		if err = query.Scan(&tmpTarget.id, &tmpTarget.title, &tmpTarget.summary); err != nil {
			fmt.Println(err) // Handle scan error
		}
		result = append(result, tmpTarget)
	}
	return result
}

func insert() {
	sqlStr := "insert into type_name(id,name) value(?,? )"
	exec, err := MysqlDb.Exec(sqlStr, "4", "测试go连接mysql")
	if err != nil {
		fmt.Println(err)
		return
	}
	id, err := exec.LastInsertId()
	if err != nil {
		fmt.Printf("getLastId error , %v\n", err)
		return
	}
	fmt.Printf("lastInsertId:%v\n", id)
}

func delete() {
	sqlStr := "delete from type_name where id = ?"
	exec, err := MysqlDb.Exec(sqlStr, "4")
	if err != nil {
		fmt.Println(err)
		return
	}
	affected, err := exec.RowsAffected()
	if err != nil {
		fmt.Println(nil)
		return
	}
	fmt.Printf("affect row : %v\n", affected)
}
