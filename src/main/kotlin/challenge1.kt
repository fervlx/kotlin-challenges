val products = ArrayList<Triple<String,Int,Double>>()

fun requestData( data: String ) : String {
    println( "Ingrese $data" )
    return  readLine() ?: ""
}

fun requestProduct() {

    println("Ingrese productos")
    val productName = requestData("nombre del producto")
    val productQuantity = requestData( "cantidad" )
    val productPrice = requestData("precio unitario")

    if (productName.isNotEmpty() && productQuantity.isNotEmpty() && productPrice.isNotEmpty()) {

        try {

            addProduct( productName,
                productQuantity.toInt(),
                productPrice.toDouble()
            )

        } catch ( e: NumberFormatException) {
            e.printStackTrace()
        }
    }
}

fun addMore() : Boolean {

    println("Agregar otro producto? 1 = SI - 2 = NO")
    val answer = readLine() ?: "2"

    return try {
        answer.toInt() == 1
    } catch ( ne: NumberFormatException ) {
        false
    }
}

fun addProduct( name: String, quantity: Int, price: Double ) {
    val product = Triple(name, quantity, price)
    products.add(product)
}

fun sumProducts() : Double  {
    return products.map { product -> product.second * product.third }.sum()
}

fun calcTax( subTotal: Double ) = subTotal * 0.1

fun calcTotal( subTotal: Double, tax: Double ) = subTotal + tax

fun printPurchasedItems( subTotal: Double, tax: Double, total: Double ) {

    products.forEach { product ->
        println("${product.first} cantidad: ${product.second}  precio unitario: ${product.third}  total: ${product.second * product.third}")
    }

    println("subtotal de  compra: $subTotal")
    println("impuestos de compra: $tax")
    println("total de compra:     $total")
}

fun main() {

    val name = requestData( "nombre")
    val birthDate = requestData("Fecha de nacimiento (dia/Mes/año)")
    val phone = requestData("Número de contacto")

    do {
        requestProduct()
    } while ( addMore() )

    val subTotal = sumProducts()
    val tax = calcTax( subTotal )
    val total = calcTotal( subTotal, tax )

    println("Cliente: $name   fecha de nacimiento: $birthDate    contacto: $phone")
    printPurchasedItems( subTotal, tax, total )
}