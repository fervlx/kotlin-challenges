object InputUtils {

    infix fun requestData( data: String ) : String {
        println( "Ingrese $data" )
        return  readLine() ?: ""
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
}

data class Product( val name: String, val quantity: Int, val price: Double )

class Client {

    var name = ""
    var birthDate = ""
    var phone = ""

    fun requestInfo() {
        name      = InputUtils requestData "nombre"
        birthDate = InputUtils requestData "Fecha de nacimiento (dia/Mes/año)"
        phone     = InputUtils requestData "Número de contacto"
    }
}

class ProductsList {

    private var subTotal = 0.0
    private var total = 0.0
    private var tax = 0.0
    private val list = ArrayList<Product>()
    private val client = Client()

    private fun addProduct( name:String, quantity: String, price: String ) {
        try {
            list.add( Product( name, quantity.toInt(), price.toDouble() ))
        } catch ( e: NumberFormatException) {
            e.printStackTrace()
        }
    }

    private fun calcTax() {
        tax = subTotal * 0.1
    }

    private fun calcTotal() {
        total = subTotal + tax
    }

    private fun calcSubTotal() {
        subTotal = list.map { product -> product.quantity * product.price  }.sum()
    }

    fun requestProduct() {

        val productName     = InputUtils requestData "Nombre del producto"
        val productPrice    = InputUtils requestData "Precio unitario"
        val productQuantity = InputUtils requestData "Cantidad"

        if ( productName.isNotEmpty() && productQuantity.isNotEmpty() && productPrice.isNotEmpty() ) {
            addProduct( productName, productQuantity, productPrice )
        }
    }

    fun printDetails() {

        calcSubTotal()
        calcTax()
        calcTotal()

        println("Cliente: ${client.name}   fecha de nacimiento: ${client.birthDate}    contacto: ${client.phone}")

        list.forEach { product ->
            println("${product.name} | cantidad: ${product.quantity}  precio unitario: ${product.price}  | total: ${product.quantity * product.price}")
        }

        println("subtotal de  compra: $subTotal")
        println("impuestos de compra: $tax" )
        println("total de compra:     $total")
    }

    fun requestClient() {
        client.requestInfo()
    }
}

fun main() {

    val products = ProductsList()
    products.requestClient()

    do {
        products.requestProduct()
    } while ( InputUtils.addMore() )

    products.printDetails()
}