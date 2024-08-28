var familia = mutableListOf<Cliente>()
var hotel = Hotel()
var sistema = true

fun main() {


    while (sistema) {
        print(
            """
        1- cadastrar;
        2- pesquisar;
        3-listar;
        4-area_eventos
        5-Area de Passeios
        6-Manutenção
        7-sair.
        
    Digite: """.trimIndent()
        )
        var escolhaOperacao: Int = readln().toInt()
        when (escolhaOperacao) {
            1 -> {
                var cadastrar_familia: Boolean = true
                while (cadastrar_familia) {
                    dadosClientes()

                    print("Deseja continuar cadastrando(S/N)? ")
                    var decisao = readln().lowercase()
                    while ((decisao != "s") and (decisao != "n")) {
                        println("Valor invalido")
                        print("Deseja continuar cadastrando(S/N)? ")
                        decisao = readln().lowercase()

                    }
                    if (decisao == "n") {
                        cadastrar_familia = false
                    }

                }
                print("Digite o valor da diaria: ")
                var valor: Double = readln().toDouble()
                print("Digite a quantidade de dias: ")
                var dias: Int = readln().toInt()

                for (v in familia) {
                    hotel.validation(v, v.nome, v.idade, v.QuantidadeDinheiro, valor, dias)

                }
            }

            2 -> {
                print("Digite o nome da pessoa: ")
                var pesquisaNome = readln()

                if (hotel.verificaPessoaEstaHospedada(pesquisaNome)) {
                    println("$pesquisaNome esta hospedada")
                } else {
                    println("$pesquisaNome Não foi encontrado")
                }
            }

            3 -> hotel.mostrarTodosHospedes()
            4 -> {
                Auditorio().areaEventos()
            }
            5-> hotel.areaPasseios()
            6 -> hotel.manutencaoAparelho()
            7 -> {
                sistema = false
                main()
            }

        }
    }
}

fun dadosClientes(){

    print("Nome: ")
    var nome = readln()
    print("Idade: ")
    var idade = readln().toInt()

    print("Qual sua reserva de dinheiro: ")
    var money:Double = readln().toDouble()

    var dados_cliente = Cliente(nome, idade, money)
    familia.add(dados_cliente)
}


