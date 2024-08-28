class Auditorio (){
    private var nomeEmpresa = ""
    private var diasDaSemanaOcupado = mutableListOf<String>()
    private var diasDaSemana = mutableListOf<String>("segunda", "terca", "quarta", "quinta", "sexta", "sabado", "domingo")
    private var horariosDiasSemana = mutableListOf<Int>(7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)
    private var horariosFinalSemana = mutableListOf<Int>(7,8,9,10,11,12,13,14,15)
    private var horariosDiasSemanaOcupados = mutableListOf<Int>()
    private var horariosFinalSemanaOcupados =  mutableListOf<Int>()
    private var quantidadePessoas = 0
    private var localEvento = ""
    private var dia = ""
    private var horario = 0
    private var duracao = 0
    private var totalFuncionarios = 0
    private var valorTotalFuncionarios = 0.0
    private var valorTotalConvidados = 0.0




    fun espacoAuditoria(quantidade:Int){
        var quant = quantidade / 220
        if (quant <= 1){
            if (quantidade <= 150){

                print("escolha a auditoria laranja com 150 espaços sua quantidade é $quantidade")
                quantidadePessoas = quantidade
            } else{
                var adicional = 220 - quantidade
                print("há 150 espaços livre e $adicional adicionais")
                localEvento = "Local do evento: -area laranja-"
                quantidadePessoas = quantidade
            }

        } else if (quantidade <= 350 ){
            print("auditoria colorado")
            print("há $quantidade de espaços livres e sobra ${350-quantidade}")
            quantidadePessoas = quantidade
            localEvento = "Local do evento: area Colorado"
        } else {
            print("Quantidade acime que a permitida")
        }

    }
    fun escolhaDia():String {
        for (v in diasDaSemana) {
            println(v)
        }
        print("Digite o dia que deseja: ")
        var diasSemana: String = readln()
        while (diasSemana !in diasDaSemana) {
            print("--Valor invalido--")
            print("Digite denovo: ")
            diasSemana = readln().lowercase()
        }
        diasDaSemanaOcupado.add(diasSemana)
        diasDaSemana.remove(diasSemana)
        dia = diasSemana
        return diasSemana
    }
    fun horariosDiasDaSemana(): Int{
        for (v in horariosDiasSemana){
            println("Horario: $v")
            print("\n")
        }
        print("Escolha um horario: ")
        var horario = readln().toInt()
        while (horario !in horariosDiasSemana){
            if (horario in horariosDiasSemanaOcupados){
                println("Horario indisponivel")
            }
            println("Valor invalido...")
            print("Escolha um horario: ")
            horario = readln().toInt()
        }
        horariosDiasSemanaOcupados.add(horario)
        horariosDiasSemana.remove(horario)
        return horario
    }
    fun horariosFinalDaSemana(): Int{
        for (v in horariosFinalSemana){
            println("Horario: $v")
            print("\n")
        }
        print("Escolha um horario: ")
        var horario = readln().toInt()
        while (horario !in horariosFinalSemana){
            if (horario in horariosFinalSemanaOcupados){
                println("Horario indisponivel")
            }
            println("Valor invalido...")
            print("Escolha um horario: ")
            horario = readln().toInt()
        }

        horariosFinalSemanaOcupados.add(horario)
        horariosFinalSemana.remove(horario)
        this.horario = horario
        return horario
    }
    fun mensagemReserva(dia:String, Hora:Int){
        println("Local reservado para $nomeEmpresa na $dia as ${Hora}h")
    }
    fun CalculoFuncionariosCusto(){

        println("Digite a quantidade de horas do evento: ")
        var quantHoraEvento = readln().toInt()
        duracao = quantHoraEvento

        totalFuncionarios = 12 + (quantHoraEvento/2)
        valorTotalFuncionarios = totalFuncionarios*10.50
        println("Total: $totalFuncionarios garçons e valor - ${totalFuncionarios*10.50}")

    }
    fun areaEventos(){
        println("Area de eventos")
        print("Digite o nome da sua empresa: ")
        nomeEmpresa = readln()

        var horario = 0
        print("Digite a quantidade de pessoas no evento: ")
        var quanPpessoa:Int = readln().toInt()
        espacoAuditoria(quanPpessoa)
        var dia = escolhaDia()
        if ((dia == "sabado") or (dia == "domingo")){
            horario = horariosFinalDaSemana()
        } else {
            horario = horariosDiasDaSemana()
        }
        mensagemReserva(dia, horario)
        CalculoFuncionariosCusto()
        println("Agora Vamos Ao Buffet :D... ")
        areaBuffet()

    }
    fun areaBuffet(){
        println("quantidade de pessoas: $quantidadePessoas")
        println("O evento precisara de ${(0.2 * quantidadePessoas)/0.80} cafes, ${(0.5 * quantidadePessoas)/0.40}l agua, ${(7 * quantidadePessoas)/34} salgados")
        valorTotalConvidados = ((0.2 * quantidadePessoas)/0.80) + ((0.5 * quantidadePessoas)/0.40) + ((7 * quantidadePessoas)/34)
        relatorioGeral()
    }
    fun relatorioGeral(){
        print("""
            $localEvento
            Nome empresa: $nomeEmpresa
            Data: $dia, $horario
            Duração: $duracao
            Quantidade de Funcionarios: $totalFuncionarios
            Quantidade de Convidados: : $quantidadePessoas
            
            Total custo com Funcionarios: $valorTotalFuncionarios
            Total custo com convidadeos: $valorTotalConvidados
            
        """.trimIndent())

    }
}