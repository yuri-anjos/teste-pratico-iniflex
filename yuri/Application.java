package yuri;

import yuri.pessoa.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
	private static final List<Funcionario> LISTA = new ArrayList<>();

	public static void main(String[] args) {

//		3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
		LISTA.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"));
		LISTA.add(new Funcionario("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador"));
		LISTA.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador"));
		LISTA.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor"));
		LISTA.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista"));
		LISTA.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador"));
		LISTA.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador"));
		LISTA.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3017.45), "Gerente"));
		LISTA.add(new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista"));
		LISTA.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente"));

//		3.2 – Remover o funcionário “João” da lista.
		LISTA.remove(1);
		//ou
		LISTA.removeIf(funcionario -> "João".equals(funcionario.getNome()));

//		3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
//			• informação de data deve ser exibido no formato dd/mm/aaaa;
//			• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
		printarLista(LISTA);

//		3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
		aplicarAumentoSalario(1.1);
		printarLista(LISTA);

//		3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
		Map<String, List<Funcionario>> agrupadosPorFuncao = LISTA.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

//		3.6 – Imprimir os funcionários, agrupados por função.
		printarMap(agrupadosPorFuncao);

//		3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
		List<Funcionario> aniversario10ou12 = LISTA.stream().filter(funcionario -> {
			int mes = funcionario.getDataNascimento().getMonthValue();
			return mes == 10 || mes == 12;
		}).toList();
		System.out.println("Aniversariantes mes 10 e 12:");
		printarLista(aniversario10ou12);

//		3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
		Funcionario maisVelho = LISTA.stream().min(Comparator.comparing(Funcionario::getDataNascimento)).get();
		System.out.println("Funcionario mais velho: " + maisVelho);

//		3.10 – Imprimir a lista de funcionários por ordem alfabética.
		List<Funcionario> ordemAlfabetica = new ArrayList<>(LISTA);
		ordemAlfabetica.sort(Comparator.comparing(Funcionario::getNome));
		System.out.println("Lista de funcionários por ordem alfabética: ");
		printarLista(ordemAlfabetica);

//		3.11 – Imprimir o total dos salários dos funcionários.
		BigDecimal somaSalario = LISTA.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println("Soma dos salários: " + somaSalario);

//		3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
		LISTA.forEach(funcionario -> System.out.printf("%s recebe %.2f salários mínimos! %n",
				funcionario.getNome(),
				funcionario.calcularNumeroSalarioMinimo())
		);
	}

	private static void printarLista(List<Funcionario> lista) {
		System.out.println("[");
		lista.forEach(System.out::println);
		System.out.println("]\n");
	}

	private static void printarMap(Map<String, List<Funcionario>> map) {
		map.forEach((funcao, funcionarios) -> {
			System.out.println(funcao + ": ");
			printarLista(funcionarios);
		});
	}

	private static void aplicarAumentoSalario(double fator) {
		LISTA.forEach(funcionario -> funcionario.setSalario(
				funcionario.getSalario().multiply(BigDecimal.valueOf(fator))));
	}
}
