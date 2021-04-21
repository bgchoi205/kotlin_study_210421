import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/*
명령어
article write
aa
asdf
article write
qq
qwer
article list

 */


fun readLineTrim() = readLine()!!.trim()


fun main(){

    println("== 게시판 관리 프로그램 시작 ==")

    // 가장 마지막에 입력된 게시물 번호
    var articlesLastId = 0

    val articles = mutableListOf<Article>()

    loop@ while (true) {
        print("명령어) ")
        val command = readLineTrim()

        when (command) {
            "system exit" -> {
                println("프로그램을 종료합니다.")
                break@loop
            }
            "article write" -> {
                val id = articlesLastId + 1
                print("제목 : ")
                val title = readLineTrim()
                print("내용 : ")
                val body = readLineTrim()
                val regDate = getRegDate()
                val article = Article(id, title, body, regDate)

                println("${id}번 게시물이 작성되었습니다.")

                articles.add(article)

                articlesLastId = id
            }
            "article list" -> {
                println("번호 / 제목 / 날짜")

                for ( article in articles ) {
                    println("${article.id} / ${article.title} / ${article.regDate}")
                }
            }
            "article delete" -> {
                val delNumber = readLine()!!.trim().toInt()
                if(delNumber > articlesLastId){
                    println("${delNumber}번 글은 존재하지 않습니다.")
                }
                else{
                    for(i in articles.indices){
                        if(articles[i].id == delNumber){
                            articles.removeAt(i)
                        }
                    }
                }
            }
            else -> {
                println("`$command` 은(는) 존재하지 않는 명령어 입니다.")
            }
        }
    }

    println("== 게시판 관리 프로그램 끝 ==")



}

data class Article(
    val id: Int,
    val title: String,
    val body: String,
    val regDate : String
)

fun getRegDate() : String{
    var now = LocalDateTime.now()

    var Strnow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    return Strnow
}