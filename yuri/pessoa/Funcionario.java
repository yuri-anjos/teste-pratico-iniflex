package yuri.pessoa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;

public class Funcionario extends Pessoa {
	private static final DecimalFormat FORMATADOR_SALARIO = new DecimalFormat("#,##0.00");
	private static final BigDecimal SALARIO_MINIMO = BigDecimal.valueOf(1212.00);

	private BigDecimal salario;
	private String funcao;

	public Funcionario() {
	}

	public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
		super(nome, dataNascimento);
		this.salario = salario;
		this.funcao = funcao;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public String toString() {
		return "{" +
				" nome: " + nome +
				", dataNascimento: " + FORMATADOR_DATA.format(dataNascimento) +
				", salario: " + FORMATADOR_SALARIO.format(salario) +
				", funcao: " + funcao +
				" }";
	}

	public BigDecimal calcularNumeroSalarioMinimo() {
		return this.salario.divide(SALARIO_MINIMO, RoundingMode.HALF_UP);
	}

	public int calcularIdade() {
		LocalDate hoje = LocalDate.now();
		return Period.between(dataNascimento, hoje).getYears();
	}
}
