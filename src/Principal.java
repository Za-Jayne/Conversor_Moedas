import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Consulta novaConsulta = new Consulta();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n***** Seja bem-vindo/a ao Conversor de moedas  *****");

            System.out.println("1 ) Real =>> Dolar");
            System.out.println("2 ) Real =>> Euro");
            System.out.println("3 ) Real =>> Libra");
            System.out.println("4 ) Real =>> Iene");
            System.out.println("5 ) Real =>> Franco Suíço");
            System.out.println("6 ) Sair");
            System.out.println("Escolha uma opção valida:");

            int escolha = scanner.nextInt();
            scanner.nextLine() ;

            switch (escolha) {
                case 1:
                    realizarConversao(novaConsulta, "USD");
                    break;
                case 2:
                    realizarConversao(novaConsulta, "EUR");
                    break;
                case 3:
                    realizarConversao(novaConsulta, "GBP");
                    break;
                case 4:
                    realizarConversao(novaConsulta, "JPY");
                    break;
                case 5:
                    realizarConversao(novaConsulta, "CHF");
                    break;
                case 6:
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção não encontrada! Tente novamente.");
            }
        }
    }

    private static void realizarConversao(Consulta consulta, String moedaDestino) {
        API consultaMoeda = consulta.consultaValor("BRL");

        if (consultaMoeda != null && consultaMoeda.conversionRates() != null) {
            Double taxaDeCambio = (Double) consultaMoeda.conversionRates().get(moedaDestino);

            if (taxaDeCambio != null) {
                double valorEmReais;
                Scanner scanner = new Scanner(System.in);

                System.out.println("Digite o valor que deseja converter: ");
                valorEmReais = scanner.nextDouble();

                double valorConvertido = valorEmReais * taxaDeCambio;
                System.out.println("O valor de " + valorEmReais + " BRL" + " convertido para " + moedaDestino +
                        " corresponde ao valor final  de: " + valorConvertido);
                System.out.println("\n********************************");
            } else {
                System.out.println("Taxa de câmbio para " + moedaDestino + " não disponível.");
                System.out.println("\n********************************");
            }
        } else {
            System.out.println("Erro ao obter as taxas de câmbio.");
            System.out.println("\n********************************");
        }
    }
}