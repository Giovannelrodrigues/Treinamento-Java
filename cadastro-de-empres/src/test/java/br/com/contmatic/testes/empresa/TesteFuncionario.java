package br.com.contmatic.testes.empresa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.enums.TipoEndereco;
import br.com.contmatic.model.Cidade;
import br.com.contmatic.model.Email;
import br.com.contmatic.model.Endereco;
import br.com.contmatic.model.Estado;
import br.com.contmatic.model.Telefone;
import br.com.contmatic.model.empresa.AmbienteTrabalho;
import br.com.contmatic.model.empresa.Cargo;
import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.empresa.Funcionario;
import br.com.contmatic.model.empresa.Setor;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteFuncionario {

	private static final Email EMAIL = new Email("giovannelrodrigues@gmail.com");

	private static final LocalDateTime LOCAL_TIME = LocalDateTime.of(2017, Month.FEBRUARY, 3, 6, 30, 40, 50000);

	private static Empresa empresaBefore;

	private static AmbienteTrabalho ambienteBefore;

	private static Setor setorBefore;

	private static Cargo cargoBefore;

	private static Funcionario funcionarioBefore;

	private static final String CPF = "62709667010";

	private static final String DATA_NASCIMENTO = "20062003";

	@Before
	public void antesDeCadaTeste() {
		empresaBefore = new Empresa(EMAIL, LOCAL_TIME, "09535558000146");
		ambienteBefore = new AmbienteTrabalho(EMAIL, LOCAL_TIME, "Desenvolvimento", empresaBefore);
		setorBefore = new Setor(EMAIL, LOCAL_TIME, "Infraestrutura", ambienteBefore);
		cargoBefore = new Cargo(EMAIL, LOCAL_TIME, "desenvolvedor front end");
		funcionarioBefore = new Funcionario(EMAIL, LOCAL_TIME, CPF, DATA_NASCIMENTO, setorBefore);
		funcionarioBefore.setCargo(cargoBefore);
		funcionarioBefore.setEndereco(new Endereco(EMAIL, LOCAL_TIME, "04852510", "rua x", 194, "JD lucelia",
				new Estado("São Paulo"), new Cidade("São Paulo"), TipoEndereco.COMERCIAL));
		List<Telefone> telefones = new ArrayList<>();
		List<Email> emails = new ArrayList<>();
		telefones.add(new Telefone(EMAIL, LOCAL_TIME, "1159160668"));
		emails.add(EMAIL);
		funcionarioBefore.setTelefones(telefones);
		funcionarioBefore.setEmails(emails);
	}

	// CONSTRUTOR
	@Test
	public void teste01_deve_instanciar_um_novo_funcionario() {
		Funcionario funcionario = new Funcionario(EMAIL, LOCAL_TIME, CPF, DATA_NASCIMENTO, setorBefore);
		assertEquals(CPF, funcionario.getCpf());
		assertEquals(DATA_NASCIMENTO, funcionario.getDataNacimento());
		assertEquals(setorBefore, funcionario.getSetor());
	}

	@Test
	public void teste02_deve_atribuir_um_novo_valor_para_cpf_funcionario() {
		String cpf = "00189936053";
		funcionarioBefore.setCpf(cpf);
		assertEquals(cpf, funcionarioBefore.getCpf());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste03_nao_deve_atribuir_um_cpf_passando_nulo() {
		funcionarioBefore.setCpf(null);
	}

	@Test(expected = IllegalStateException.class)
	public void teste04_nao_deve_atribuir_um_cpf_passando_string_vazia() {
		funcionarioBefore.setCpf("   ");
	}

	@Test(expected = IllegalStateException.class)
	public void teste05_nao_deve_atribuir_um_passando_mais_de_11_caracteres() {
		funcionarioBefore.setCpf("123123123123123");
	}

	@Test(expected = IllegalStateException.class)
	public void teste06_nao_deve_atribuir_um_novo_cnpj_passando_menos_de_11_caracteres() {
		funcionarioBefore.setCpf("123123123123");
	}

	@Test(expected = IllegalStateException.class)
	public void teste07_nao_deve_atribuir_um_novo_cnpj_passando_formatado() {
		funcionarioBefore.setCpf("637.516.934-09");
	}

	@Test(expected = IllegalStateException.class)
	public void teste08_nao_deve_atribuir_um_novo_cnpj_passando_letra() {
		funcionarioBefore.setCpf("123123ABC12");
	}

	@Test(expected = IllegalStateException.class)
	public void teste09_nao_deve_atribuir_um_novo_cnpj_passando_numeros_repetidos() {
		funcionarioBefore.setCpf("99999999999");
	}

	@Test(expected = IllegalStateException.class)
	public void teste10_nao_deve_atribuir_um_novo_cnpj_passando_cnpj_invalido() {
		funcionarioBefore.setCpf("12345678912");
	}

	// NOME
	@Test
	public void teste11_deve_atribuir_um_novo_nome_para_funcionario() {
		String nome = "Pedro Augusto";
		funcionarioBefore.setNome(nome);
		assertEquals(nome, funcionarioBefore.getNome());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste12_nao_deve_atribuir_um_novo_nome_para_funcionario_passando_nulo() {
		ambienteBefore.setNome(null);
	}

	@Test(expected = IllegalStateException.class)
	public void teste13_nao_deve_atribuir_um_novo_nome_para_funcionario_passando_vazio() {
		funcionarioBefore.setNome("    ");
	}

	@Test(expected = IllegalStateException.class)
	public void teste14_nao_deve_atribuir_um_novo_nome_para_funcionario_passando_mais_100_caracteres() {
		funcionarioBefore.setNome(
				"RH RHRH RHRH RHRH RHRH RHRH RHRH RHRH RHRH RHRH RHRH RHRH RHRH RHRH RH  RHRH RHRH RHRH RHRH RHRH RHRH RHRH RH  RHRH RHRH RHRH RHRH RHRH RHRH RHRH RH");
	}

	@Test(expected = IllegalStateException.class)
	public void teste15_nao_deve_atribuir_um_novo_nome_para_funcionario_passando_menos_3_caracteres() {
		funcionarioBefore.setNome("Ab");
	}

	@Test(expected = IllegalStateException.class)
	public void teste16_nao_deve_atribuir_um_novo_nome_para_funcionario_passando_caracteres_especiais() {
		funcionarioBefore.setNome("RECURSO # HUMANOS");
	}

	@Test(expected = IllegalStateException.class)
	public void teste17_nao_deve_atribuir_um_novo_nome_para_funcionario_passando_numeros() {
		funcionarioBefore.setNome("123456abc");
	}

	// ENDERECO
	@Test
	public void teste18_deve_atribuir_um_novo_endereco_para_funcionario() {
		Endereco endereco = new Endereco(EMAIL, LOCAL_TIME, "04852510", "rua x", 194, "JD lucelia",
				new Estado("São Paulo"), new Cidade("São Paulo"), TipoEndereco.COMERCIAL);
		funcionarioBefore.setEndereco(endereco);
		assertEquals(endereco, funcionarioBefore.getEndereco());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste19_nao_deve_atribuir_um_novo_endereco_para_funcionario_passando_nulo() {
		funcionarioBefore.setEndereco(null);
	}

	// LISTA TELEFONE
	@Test
	public void teste20_deve_atribuir_uma_nova_lista_de_endereco_para_funcionario() {
		List<Telefone> telefones = new ArrayList<>();
		telefones.add(new Telefone(EMAIL, LOCAL_TIME, "1159160668"));
		funcionarioBefore.setTelefones(telefones);
		assertEquals(telefones, funcionarioBefore.getTelefones());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste21_nao_deve_atribuir_uma_nova_lista_de_telefone_passando_nulo() {
		funcionarioBefore.setTelefones(null);
	}

	@Test(expected = IllegalStateException.class)
	public void teste22_nao_deve_atribuir_uma_nova_lista_de_telefone_lista_vazia() {
		List<Telefone> telefones = new ArrayList<>();
		funcionarioBefore.setTelefones(telefones);
	}

	@Test(expected = IllegalStateException.class)
	public void teste23_nao_deve_atribuir_uma_nova_lista_de_telefones_coma_mais_de_5_telefones() {
		List<Telefone> telefones = new ArrayList<>();
		for (int index = 7; index != 0; index--) {
			telefones.add(new Telefone(EMAIL, LOCAL_TIME, "1159160668"));
		}
		funcionarioBefore.setTelefones(telefones);
	}

	// LISTA EMAILS
	@Test
	public void teste24_deve_atribuir_uma_nova_lista_de_email_para_funcionario() {
		List<Email> emails = new ArrayList<>();
		emails.add(new Email("funcionario@cont.com"));
		funcionarioBefore.setEmails(emails);
		assertEquals(emails, funcionarioBefore.getEmails());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste25_nao_deve_atribuir_uma_nova_lista_de_emails_passando_nulo() {
		funcionarioBefore.setEmails(null);
	}

	@Test(expected = IllegalStateException.class)
	public void teste26_nao_deve_atribuir_uma_nova_lista_de_emails_lista_vazia() {
		List<Email> emails = new ArrayList<Email>();
		funcionarioBefore.setEmails(emails);
	}

	@Test(expected = IllegalStateException.class)
	public void teste27_nao_deve_atribuir_uma_nova_lista_de_telefones_coma_mais_de_5_telefones() {
		List<Email> emails = new ArrayList<>();
		for (int index = 7; index != 0; index--) {
			emails.add(new Email("emailcont@cont.com"));
		}
		funcionarioBefore.setEmails(emails);
	}

	// TESTE CARGO
	@Test
	public void teste28_deve_atribuir_um_novo_cargo_para_funcionario() {
		Cargo cargo = new Cargo(EMAIL, LOCAL_TIME, "Gerente de infraestrutura");
		funcionarioBefore.setCargo(cargo);
		assertEquals(cargo, funcionarioBefore.getCargo());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste29_nao_deve_atribuir_um_cargo_passando_nulo() {
		funcionarioBefore.setCargo(null);
	}

	// TESTE SETOR
	@Test
	public void teste30_deve_atribuir_um_novo_setor_para_funcionario() {
		Setor setor = new Setor(EMAIL, LOCAL_TIME, "Infra", ambienteBefore);
		funcionarioBefore.setSetor(setor);
		assertEquals(setor, funcionarioBefore.getSetor());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste31_nao_deve_atribuir_um_cargo_passando_nulo() {
		funcionarioBefore.setSetor(null);
	}

	// DATA NASCIMENTO
	@Test
	public void teste31_deve_atribuir_uma_nova_data_de_nascimento() {
		funcionarioBefore.setDataNacimento(DATA_NASCIMENTO);
		assertEquals(DATA_NASCIMENTO, funcionarioBefore.getDataNacimento());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste32_nao_deve_atribuir_uma_nova_data_de_nascimento_passando_nulo() {
		funcionarioBefore.setDataNacimento(null);
	}

	@Test(expected = IllegalStateException.class)
	public void teste33_nao_deve_atribuir_uma_nova_data_de_nascimento_passando_vazio() {
		funcionarioBefore.setDataNacimento("   ");
	}

	@Test(expected = IllegalStateException.class)
	public void teste34_nao_deve_atribuir_uma_nova_data_de_nascimento_passando_mais_de_8_caracteres() {
		funcionarioBefore.setDataNacimento("1212202145");
	}

	@Test(expected = IllegalStateException.class)
	public void teste35_nao_deve_atribuir_uma_nova_data_de_nascimento_passando_menos_de_8_caracteres() {
		funcionarioBefore.setDataNacimento("20062");
	}

	@Test(expected = IllegalStateException.class)
	public void teste36_nao_deve_atribuir_uma_nova_data_de_nascimento_passando_formatado() {
		funcionarioBefore.setDataNacimento("20/06/2003");
	}

	@Test(expected = IllegalStateException.class)
	public void teste37_nao_deve_atribuir_uma_nova_data_de_nascimento_passando_data_com_menos_de_14_anos() {
		funcionarioBefore.setDataNacimento("200620015");
	}

	@Test(expected = IllegalStateException.class)
	public void teste38_nao_deve_atribuir_uma_nova_data_de_nascimento_passando_data_maior_que_hoje() {
		funcionarioBefore.setDataNacimento("20062023");
	}

	@Test(expected = IllegalStateException.class)
	public void teste39_nao_deve_atribuir_uma_nova_data_de_nascimento_passando_letras() {
		funcionarioBefore.setDataNacimento("20A62A23");
	}

	@Test(expected = IllegalStateException.class)
	public void teste40_nao_deve_atribuir_uma_nova_data_de_nascimento_passando_data_invalida() {
		funcionarioBefore.setDataNacimento("34242023");
	}

	// TO STRING

	@Test
	public void teste41_deve_retonar_cpf_no_to_string() {
		String result = funcionarioBefore.toString();
		assertThat(result, containsString(funcionarioBefore.getCpf()));
	}

	@Test
	public void teste42_deve_retonar_nome_no_to_string() {
		funcionarioBefore.setNome("Giovanne rodrigues");
		String result = funcionarioBefore.toString();
		assertThat(result, containsString(funcionarioBefore.getNome()));
	}

	@Test
	public void teste43_deve_retonar_cargo_no_to_string() {
		String result = funcionarioBefore.toString();
		assertThat(result, containsString(String.valueOf(funcionarioBefore.getCargo())));
	}

	@Test
	public void teste44_deve_retonar_data_nascimento_no_to_string() {
		String result = funcionarioBefore.toString();
		assertThat(result, containsString(funcionarioBefore.getDataNacimento()));
	}

	@Test
	public void teste45_deve_retonar_endereco_no_to_string() {
		String result = funcionarioBefore.toString();
		assertThat(result, containsString(String.valueOf(funcionarioBefore.getEndereco())));
	}

	@Test
	public void teste46_deve_retonar_lista_telefones_no_to_string() {
		String result = funcionarioBefore.toString();
		assertThat(result, containsString(String.valueOf(funcionarioBefore.getTelefones())));
	}

	@Test
	public void teste47_deve_retonar_lista_emails_no_to_string() {
		String result = funcionarioBefore.toString();
		assertThat(result, containsString(String.valueOf(funcionarioBefore.getEmails())));
	}

	// HASHCODE
	@Test
	public void teste48_deve_retornar_o_mesmo_hashcode_para_funcionario_com_mesmo_cpf() {
		Funcionario funcionario1 = new Funcionario(EMAIL, LOCAL_TIME, CPF, "20062003", setorBefore);
		Funcionario funcionario2 = new Funcionario(EMAIL, LOCAL_TIME, CPF, "20062003", setorBefore);
		boolean resp = funcionario1.hashCode() == funcionario2.hashCode();
		assertTrue(resp);
	}

	@Test
	public void teste49_deve_retornar_o_mesmo_hashcode_para_funcionario_com_cpf_diferentes() {
		Funcionario funcionario1 = new Funcionario(EMAIL, LOCAL_TIME, CPF, "20062003", setorBefore);
		Funcionario funcionario2 = new Funcionario(EMAIL, LOCAL_TIME, "30524250057", "20062003", setorBefore);
		boolean resp = funcionario1.hashCode() == funcionario2.hashCode();
		assertFalse(resp);
	}

	// EQUALS
	@Test
	public void teste50_deve_retornar_true_quando_usar_equals_passado_cpf_iguais() {
		Funcionario funcionario1 = new Funcionario(EMAIL, LOCAL_TIME, CPF, "20062003", setorBefore);
		Funcionario funcionario2 = new Funcionario(EMAIL, LOCAL_TIME, CPF, "20062003", setorBefore);
		boolean resp = funcionario1.equals(funcionario2);
		assertTrue(resp);
	}

	@Test
	public void teste51_deve_retornar_falso_quando_usar_equals_passado_cpf_diferente_difentes() {
		Funcionario funcionario1 = new Funcionario(EMAIL, LOCAL_TIME, CPF, "20062003", setorBefore);
		Funcionario funcionario2 = new Funcionario(EMAIL, LOCAL_TIME, "30524250057", "20062003", setorBefore);
		boolean resp = funcionario1.equals(funcionario2);
		assertFalse(resp);
	}

	@Test
	public void teste52_deve_retornar_falso_quando_usar_equals_passado_nulo() {
		boolean resp = funcionarioBefore.equals(null);
		assertFalse(resp);
	}

	@Test
	public void teste53_deve_retornar_falso_quando_usar_equals_passado_object() {
		boolean resp = funcionarioBefore.equals(new Object());
		assertFalse(resp);
	}

	@Test
	public void teste54_deve_retornar_verdadeiro_quando_usar_equals_passado_o_proprio_objeto() {
		boolean resp = funcionarioBefore.equals(funcionarioBefore);
		assertTrue(resp);
	}

}
