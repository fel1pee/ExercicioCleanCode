import java.util.*;

record Item(String nome, double preco) {
}

class Pedido {
    private List<Item> itens = new ArrayList<>();

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.preco();
        }
        return total;
    }

    public List<Item> getItens() {
        return itens;
    }
}

public class SistemaDePedidos {
    private static List<Pedido> pedidos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha após a leitura do número
            // Menu do programa
            switch (opcao) {
                case 1:
                    criarPedido();
                    break;
                case 2:
                    adicionarItemAoPedido();
                    break;
                case 3:
                    calcularTotalDoPedido();
                    break;
                case 4:
                    listarItensDoPedido();
                    break;
                case 5:
                    encerrarPrograma();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    // Exibição do menu de pedido
    private static void exibirMenu() {
        System.out.println("1. Criar Pedido");
        System.out.println("2. Adicionar Item ao Pedido");
        System.out.println("3. Calcular Total do Pedido");
        System.out.println("4. Listar Itens do Pedido");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void criarPedido() {
        Pedido novoPedido = new Pedido();
        pedidos.add(novoPedido);
        System.out.println("Pedido criado.");
    }

    private static void adicionarItemAoPedido() {
        if (pedidos.isEmpty()) {
            System.out.println("Crie um pedido primeiro.");
            return;
        }

        System.out.print("Digite o nome do item: ");
        String nomeItem = scanner.nextLine();
        System.out.print("Digite o preço do item: ");
        double precoItem;

        try {
            precoItem = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Preço inválido. Certifique-se de inserir um número.");
            return;
        }

        Item item = new Item(nomeItem, precoItem);
        Pedido ultimoPedido = pedidos.get(pedidos.size() - 1);
        ultimoPedido.adicionarItem(item);
        System.out.println("Item adicionado ao pedido.");
    }

    private static void calcularTotalDoPedido() {
        if (pedidos.isEmpty()) {
            System.out.println("Crie um pedido primeiro.");
            return;
        }

        Pedido pedidoAtual = pedidos.get(pedidos.size() - 1);
        double total = pedidoAtual.calcularTotal();
        System.out.println("Total do pedido: " + total);
    }

    private static void listarItensDoPedido() {
        if (pedidos.isEmpty()) {
            System.out.println("Crie um pedido primeiro.");
            return;
        }

        Pedido pedidoParaListar = pedidos.get(pedidos.size() - 1);
        List<Item> itensDoPedido = pedidoParaListar.getItens();

        System.out.println("Itens do pedido:");
        for (Item item : itensDoPedido) {
            System.out.println(item.nome() + ": " + item.preco());
        }
    }

    private static void encerrarPrograma() {
        scanner.close();
        System.exit(0);
    }
}
