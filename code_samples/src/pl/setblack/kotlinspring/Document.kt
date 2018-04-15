package pl.setblack.kotlinspring

data class Document( val id : Int, val path: String?, val type : Long)


fun fun1() {
    val doc1 = Document(7, null, 4)
    val x1:String  = if (doc1.path!=null ) doc1.path else ""
    val len = doc1.path?.length
    val len2 = doc1.path?.length ?: -42;
}