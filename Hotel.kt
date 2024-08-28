class Hotel {
    //atributos
    private var senha:Int = 2678
    private var nomeHospede = ""
    private var quartoHospede = ""
    private var valorDiaria:Double = 0.0
    private var quantDias:Int = 0
    private var totalHospedagem: Double = 0.0
    private var pessoasNoHotel = mutableListOf<String>()
    private var extratoGeral= mutableListOf<String>()
    private var quartosDisponiveis = mutableListOf<String>(
        "Quarto1", "Quarto2", "Quarto3", "Quarto4", "Quarto5",
        "Quarto6", "Quarto7", "Quarto8", "Quarto9", "Quarto10",
        "Quarto11", "Quarto12", "Quarto13", "Quarto14", "Quarto15",
        "Quarto16", "Quarto17", "Quarto18", "Quarto19", "Quarto20",
        )
    private var alcoolMaisBarata = mutableListOf<String>()
    private var gasolinaMaisBarata = mutableListOf<String>()
    private var quartosOcupados = mutableListOf<String>()

    //area deserviços
    private var listaEmpresaManutencaoNome = mutableListOf<String>()
    private var listaEmpresaManutencaoValor = mutableListOf<Double>()


    fun senhaVerify(senhatentativa: Int): Boolean {
        if (senha == senhatentativa) {
            return true
        } else {
            return false
        }
    }
    fun calculoTotal(valor:Double, dias: Int , idade: Int, quantidadeValor: Double, nome:String){
        if ((idade>= 0) and (idade <= 6)){
            valorDiaria = valor
            quantDias = dias
            totalHospedagem = 0.0
            pessoasNoHotel.add(nome)
            println("$nome ficou de graça pela sua idade")
        } else if ((idade>= 6) and (idade<=59)){
            valorDiaria = valor
            quantDias = dias
            totalHospedagem = valorDiaria*quantDias
            pessoasNoHotel.add(nome)
            println("$nome o valor ficou $totalHospedagem")
        } else {
            valorDiaria = valor
            quantDias = dias
            totalHospedagem = (valorDiaria*quantDias)/2
            pessoasNoHotel.add(nome)
            println("$nome o valor ficou ${totalHospedagem} desconto de 50%")
        }

    }
    fun validationIdade(idade: Int){

    }
    fun validation(cliente:Cliente ,nome:String, idade:Int, quantidadeValor:Double, valor:Double, dias:Int): Boolean{
        if (valor in 1.0..30.0){
            if (dias in 1..30){
                println("Valor total da reserva é ${valor*dias}")
                hospedarCliente(cliente ,nome, idade, quantidadeValor, valor,dias)
                return true
            } else {

                println("Quantidade de dias invalido")
                return false
            }
        } else {

            println("valor invalido")

            return false
        }

    }
    fun reservarQuarto(Quarto:String){
        quartosOcupados.add(Quarto)
        quartosDisponiveis.remove(Quarto)
        quartoHospede = Quarto

    }
    fun escolhaQuarto(Quarto: String): Boolean {
        if (quartosDisponiveis.contains(Quarto)) {
            return true
        } else {
            println("Quartos indisponivel...")
            return false
        }
    }
    fun reservaTotal(valor:Double, dias:Int , quarto:String, nome:String, idade: Int, quantValor: Double){
        reservarQuarto(quarto)
        calculoTotal(valor,dias, idade, quantValor, nome)
        nomeHospede = nome


    }
    fun confirmarReserva(valor:Double, dias:Int , quarto:String, nome:String, idade:Int, valorQuant: Double): Boolean{
        var confirm = mutableListOf<String>("sim", "s")
        print("Deseja confirmar: ")
        var reservar = readln().lowercase()
        if (reservar == "s"){
            reservaTotal(valor, dias, quarto, nome, idade, valorQuant)
            return true
        }else{
            return false
        }


    }
    fun hospedarCliente(cliente:Cliente, nome:String , idade:Int, quantidadeValorvalor:Double, valor: Double, dias:Int){

        mostrarQuartos()
        print("Escolha um quarto $nome: ")
        var qualQuarto = readln()

        var sucesso = escolhaQuarto(qualQuarto)
        while (!sucesso){
            print("Digite outro quarto $nome: ")
            qualQuarto = readln()
            sucesso = escolhaQuarto(qualQuarto)
        }

        var Sair = confirmarReserva(valor, dias, qualQuarto, nome ,idade, quantidadeValorvalor)
        if (!Sair){
            println("Até a proxima...")
            familia.remove(cliente)
        } else {
            println("------ Reserva feita com sucesso $nome Bem vindo. ---------")
            extrato()
        }

    }
    fun extrato(){
        var extrat = """
            Nome: $nomeHospede
            Valor da Diaria: $valorDiaria
            Quatidade de Dias: $quantDias
            Quarto Reservado: $quartoHospede
            Total de Custo: $totalHospedagem
            
        """.trimIndent()
        var extrato_pesquisa = """
            
             Nome: $nomeHospede
             Quarto Reservado: $quartoHospede
             
        """.trimIndent()
        println(extrat)

        extratoGeral.add(extrato_pesquisa)
    }
    fun mostrarTodosHospedes(){
        for (v in extratoGeral){
            println(v)
            }
        }
    fun mostrarQuartos() {
        for (v in quartosDisponiveis) {
            println(v)
        }
    }
    fun verificaPessoaEstaHospedada(nome:String): Boolean{
        if (pessoasNoHotel.contains(nome)){
            return true
    } else {
        return false
    }
    }
    fun calculoPrecoCombustivel(){
        print("Digite o valor da gasolina no Wayne Oil")
        var gasolinaWayne = readln().toDouble()
        print("Digite o valor da Alcool no Wayne Oil")
        var alcoolWayne = readln().toDouble()
        print("Digite o valor da gasolina no Stark Petrol")
        var gasolinaStark = readln().toDouble()
        print("Digite o valor da Alcool no  Stark Petrol")
        var alcoolStark = readln().toDouble()

        var maisBaratoGasolina = if (gasolinaStark > gasolinaWayne) maisBaratoGasolina("gasolinaWayne", gasolinaWayne) else maisBaratoGasolina("gasolinaStark", gasolinaStark)
        var maisBaratoAlcool = if (alcoolStark > alcoolWayne) maisBaratoAlcool("alcoolWayne", alcoolWayne) else maisBaratoAlcool("alcoolStark", alcoolStark)
        calculaMaisBarato()

    }
    fun calculaMaisBarato() {
        if ((gasolinaMaisBarata[1].toDouble() * 0.70) > alcoolMaisBarata[1].toDouble()) {
            println("Alcool da ${alcoolMaisBarata[0]} mais barato valor: ${alcoolMaisBarata[1].toDouble() * 42}")
        } else if (alcoolMaisBarata[1].toDouble() > gasolinaMaisBarata[1].toDouble()) {
            println("Gasolina da ${gasolinaMaisBarata[0]} mais barato valor: ${gasolinaMaisBarata[1].toDouble() * 42}")
        } else {
            println("Alcool da ${alcoolMaisBarata[0]} mais barato valor: ${alcoolMaisBarata[1].toDouble() * 42}")
        }
    }
    fun maisBaratoGasolina(nomeempresa:String, valor:Double){
        gasolinaMaisBarata.add(nomeempresa)
        gasolinaMaisBarata.add("$valor")

    }
    fun maisBaratoAlcool(nomeempresa:String, valor:Double){
        alcoolMaisBarata.add(nomeempresa)
        alcoolMaisBarata.add("$valor")

    }
    fun areaPasseios(){
        println("Aqui vem a area de passeios...")
        calculoPrecoCombustivel()
    }
    fun manutencaoAparelho() {
        var condicao = true

        while (condicao) {
            print("Nome da empresa: ")
            var nomeEmpresaManutecao = readln()
            print("Digite o valor da manutenção: ")
            var valorManutencao = readln().toDouble()
            print("Digite a quantidade de aparelhos: ")
            var quantAparelhos = readln().toInt()
            print("tem desconto?(caso não digite 0): ")
            var desconto: Double = readln().toDouble()
            print("Digite a quantidade minima de aparelhos para o desconto: ")
            var quantMinimaParaDesconto: Int = readln().toInt()
            calculoValorServicoManutencao(nomeEmpresaManutecao, valorManutencao, quantAparelhos, desconto, quantMinimaParaDesconto)
            print("Deseja continuar(S/N): ")
            var continuar:String = readln().trim().lowercase()
            while ((continuar != "n") && (continuar != "s")){
                println("Valor invalido...")
                print("Deseja continuar(S/N): ")
                continuar = readln().lowercase()
            }
            if (continuar == "n"){
                condicao= false
            }
        }
        verificaManutancaoMaisBarata()
    }
    fun verificaManutancaoMaisBarata(){
        var menorPreco:Double = listaEmpresaManutencaoValor[0]
        var indexMenor: Int = 0
        for ((index,valor) in listaEmpresaManutencaoValor.withIndex())
            if (valor < menorPreco){
                menorPreco = valor
                indexMenor = index
            }
        println("""
            
            O menor valor e da empresa ${listaEmpresaManutencaoNome[indexMenor]} com o valor de $menorPreco""".trimIndent())
    }
    fun calculoValorServicoManutencao(nomeEmpresaManutecao: String, valorManutencao:Double,quantAparelhos:Int,desconto:Double, quantMinimaParaDesconto:Int){
        print(desconto)
        if (quantAparelhos >= quantMinimaParaDesconto){
            val valorTotalSemDesconto = valorManutencao * quantAparelhos
            val valorDesconto = valorTotalSemDesconto * (desconto / 100)
            var total  = valorTotalSemDesconto - valorDesconto
            println("Valor do desconto - $valorDesconto")
            print(total)
            listaEmpresaManutencaoNome.add(nomeEmpresaManutecao)
            listaEmpresaManutencaoValor.add(total)
            println("$nomeEmpresaManutecao valor do serviço - $total")
        }else {
            listaEmpresaManutencaoNome.add(nomeEmpresaManutecao)
            listaEmpresaManutencaoValor.add(valorManutencao*quantAparelhos)
            println("$nomeEmpresaManutecao valor do serviço - ${valorManutencao*quantAparelhos}")
        }

    }
}

