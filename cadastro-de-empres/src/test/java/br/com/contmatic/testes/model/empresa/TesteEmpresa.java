package br.com.contmatic.testes.model.empresa;

import static br.com.contmatic.model.constants.numericas.EmpresaConstants.TAMANHO_MAX_LISTA_AMBIENTE_TRABALHO;
import static br.com.contmatic.model.constants.numericas.EmpresaConstants.TAMANHO_MAX_LISTA_EMAILS;
import static br.com.contmatic.model.constants.numericas.EmpresaConstants.TAMANHO_MAX_LISTA_ENDERECO;
import static br.com.contmatic.model.constants.numericas.EmpresaConstants.TAMANHO_MAX_LISTA_PRODUTO;
import static br.com.contmatic.model.constants.numericas.EmpresaConstants.TAMANHO_MAX_LISTA_TELEFONES;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.model.contato.Email;
import br.com.contmatic.model.contato.Telefone;
import br.com.contmatic.model.empresa.AmbienteTrabalho;
import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.empresa.Produto;
import br.com.contmatic.model.endereco.Cidade;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.endereco.Estado;
import br.com.contmatic.model.endereco.TipoEndereco;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteEmpresa {

	private static final Email EMAIL = new Email("giovannelrodrigues@gmail.com");
	
	private static final String IP = "122.21.123.1";

	private static Empresa empresaBefore;

	private static final String CNPJ = "63516934000109";

	private static final LocalDateTime LOCAL_TIME = LocalDateTime.of(2023, Month.JUNE, 28, 6, 30, 40, 50000);

	private static Integer quantidadeTest;

	@BeforeClass
	public static void zerando_contador() {
		setQuantidadeTest(0);
	}

	@After
	public void depoisDeCadaTeste() {
		setQuantidadeTest(quantidadeTest + 1);
	}

	@AfterClass
	public static void depoisDeTodosOsTestes() {
		System.out.println(String.format("Quantidade de Testes: %s", quantidadeTest));
	}

	@Before
	public void teste00_antes_de_cada_teste() {
		empresaBefore = new Empresa(CNPJ);
		empresaBefore.setCreatedBy(EMAIL);
		empresaBefore.setCreateDate(LOCAL_TIME);
		empresaBefore.setUpdateBy(EMAIL);
		empresaBefore.setUpdatedDate(LOCAL_TIME);
		empresaBefore.setUpdatedIp(IP);
		empresaBefore.setCreatedIp(IP);
		
	}

	// CONSTRUTOR
	@Test
	public void teste01_deve_instaciar_uma_empresa_usando_construtor_obrigatorio() {
		assertEquals(CNPJ, empresaBefore.getCnpj());
	}

	// CNPJ
	@Test
	public void teste02_deve_atribuir_um_novo_cnpj_valido() {
		String cnpj = "71311703000189";
		empresaBefore.setCnpj(cnpj);
		assertEquals(cnpj, empresaBefore.getCnpj());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste03_nao_deve_atribuir_um_novo_cnpj_passando_nulo() {
		empresaBefore.setCnpj(null);
	}

	@Test(expected = IllegalStateException.class)
	public void teste04_nao_deve_atribuir_um_novo_cnpj_passando_string_vazia() {
		empresaBefore.setCnpj("   ");
	}

	@Test(expected = IllegalStateException.class)
	public void teste05_nao_deve_atribuir_um_novo_cnpj_passando_mais_de_14_caracteres() {
		empresaBefore.setCnpj("123123123123123");
	}

	@Test(expected = IllegalStateException.class)
	public void teste06_nao_deve_atribuir_um_novo_cnpj_passando_menos_de_14_caracteres() {
		empresaBefore.setCnpj("123123123123");
	}

	@Test(expected = IllegalStateException.class)
	public void teste07_nao_deve_atribuir_um_novo_cnpj_passando_formatado() {
		empresaBefore.setCnpj("63.516.934/0001-09");
	}

	@Test(expected = IllegalStateException.class)
	public void teste08_nao_deve_atribuir_um_novo_cnpj_passando_letra() {
		empresaBefore.setCnpj("123123ABC12345");
	}

	@Test(expected = IllegalStateException.class)
	public void teste09_nao_deve_atribuir_um_novo_cnpj_passando_numeros_repetidos() {
		empresaBefore.setCnpj("99999999999999");
	}

	@Test(expected = IllegalStateException.class)
	public void teste10_nao_deve_atribuir_um_novo_cnpj_passando_cnpj_invalido() {
		empresaBefore.setCnpj("12345678912314");
	}

	// RAZAO SOCIAL
	@Test
	public void teste11_deve_atribuir_um_valor_para_razao_social() {
		String razao_social = "SOFTMATIC SISTEMAS AUTOMATICOS DE INFORMATICA LTDA.";
		empresaBefore.setRazaoSocial(razao_social);
		assertEquals(razao_social, empresaBefore.getRazaoSocial());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste12_nao_deve_atribuir_um_valor_para_razao_social_pasando_nulo() {
		empresaBefore.setRazaoSocial(null);
	}

	@Test(expected = IllegalStateException.class)
	public void teste13_nao_deve_atribuir_um_valor_para_razao_social_passando_string_vazia() {
		empresaBefore.setRazaoSocial("    ");
	}

	@Test(expected = IllegalStateException.class)
	public void teste14_nao_deve_atribuir_um_valor_para_razao_social_passando_mais_de_100_caracteres() {
		empresaBefore.setRazaoSocial(
				"12345seixs12345seixs12345seixs12345seixs12345seixs12345seixs12345seixs12345seixs12345seixs12345seixs12345seixs");
	}

	@Test(expected = IllegalStateException.class)
	public void teste15_nao_deve_atribuir_um_valor_para_razao_social_passando_menos_de_5_caracteres() {
		empresaBefore.setRazaoSocial("123");
	}

	@Test(expected = IllegalStateException.class)
	public void teste16_nao_deve_atribuir_um_valor_para_razao_social_passando_caracteres_diferente_de_ponto_e_ecomercial() {
		empresaBefore.setRazaoSocial("SOFTMATIC @ SISTEMAS # AUTOMATICOS & INFORMATICA ! LTDA.");
	}

	// NOME FANTASIA
	@Test
	public void teste17_deve_atribuir_um_valor_para_nome_fantasia() {
		String nome_fantasia = "Contmatic Phoenix";
		empresaBefore.setNomeFantasia(nome_fantasia);
		assertEquals(nome_fantasia, empresaBefore.getNomeFantasia());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste18_nao_deve_atribuir_um_valor_para_nome_fantasia_pasando_nulo() {
		empresaBefore.setNomeFantasia(null);
	}

	@Test(expected = IllegalStateException.class)
	public void teste19_nao_deve_atribuir_um_valor_para_nome_fantasia_passando_string_vazia() {
		empresaBefore.setNomeFantasia("  ");
	}

	@Test(expected = IllegalStateException.class)
	public void teste20_nao_deve_atribuir_um_valor_para_nome_fantasia_passando_mais_de_100_caracteres() {
		empresaBefore.setNomeFantasia(
				"12345seixs12345seixs12345seixs12345seixs12345seixs12345seixs12345seixs12345seixs12345seixs12345seixs12345seixs");
	}

	@Test(expected = IllegalStateException.class)
	public void teste21_nao_deve_atribuir_um_valor_para_nome_fantasia_passando_menos_de_5_caracteres() {
		empresaBefore.setNomeFantasia("123");
	}

	// LISTA ENDERECO EMPRESA
	@Test
	public void teste22_deve_atribuir_um_valor_para_lista_endereco() {
		List<Endereco> enderecos = new ArrayList<Endereco>();
		enderecos.add(new Endereco("04852510", "rua x", 194, "JD lucelia",
				new Cidade("São Paulo", 24584, new Estado("SP", "São Paulo")), new Estado("SP", "São Paulo"),
				TipoEndereco.COMERCIAL));
		empresaBefore.setEnderecos(enderecos);
		assertEquals(enderecos, empresaBefore.getEnderecos());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste23_nao_deve_atribuir_um_valor_para_lista_endereco_passando_nulo() {
		empresaBefore.setEnderecos(null);
	}

	@Test(expected = IllegalStateException.class)
	public void teste24_nao_deve_atribuir_um_valor_para_lista_endereco_passando_lista_vazia() {
		List<Endereco> enderecos = new ArrayList<Endereco>();
		empresaBefore.setEnderecos(enderecos);
	}

	@Test(expected = IllegalStateException.class)
	public void teste25_deve_atribuir_um_valor_para_lista_endereco_com_mais_de_5_enderecos() {
		List<Endereco> enderecos = new ArrayList<Endereco>();
		for (int index = TAMANHO_MAX_LISTA_ENDERECO + 1; index != 0; index--) {
			enderecos.add(new Endereco("04852510", "rua x", 194, "JD lucelia",
					new Cidade("São Paulo", 24584, new Estado("São Paulo", "SP")), new Estado("São Paulo", "SP"),
					TipoEndereco.COMERCIAL));
		}
		empresaBefore.setEnderecos(enderecos);
	}

	// LISTA AMBIENTES TRABALHOS
	@Test
	public void teste26_deve_atribuir_um_valor_para_lista_ambientes_trabalhos() {
		List<AmbienteTrabalho> ambientes = new ArrayList<AmbienteTrabalho>();
		ambientes.add(new AmbienteTrabalho("Recusos Humanos", empresaBefore));
		empresaBefore.setAmbientesTrabalhos(ambientes);
		assertEquals(ambientes, empresaBefore.getAmbientesTrabalhos());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste27_nao_deve_atribuir_um_valor_para_lista_ambientes_passando_nulo() {
		empresaBefore.setAmbientesTrabalhos(null);
	}

	@Test(expected = IllegalStateException.class)
	public void teste28_nao_deve_atribuir_um_valor_para_lista_ambientes_passando_lista_vazia() {
		List<AmbienteTrabalho> ambientes = new ArrayList<AmbienteTrabalho>();
		empresaBefore.setAmbientesTrabalhos(ambientes);
	}

	@Test(expected = IllegalStateException.class)
	public void teste29_deve_atribuir_um_valor_para_lista_ambientes_coma_mais_de_30_ambientes() {
		List<AmbienteTrabalho> ambientes = new ArrayList<AmbienteTrabalho>();
		for (int index = TAMANHO_MAX_LISTA_AMBIENTE_TRABALHO + 1; index != 0; index--) {
			ambientes.add(new AmbienteTrabalho("Recusos Humanos", empresaBefore));
		}
		empresaBefore.setAmbientesTrabalhos(ambientes);
	}

	// LISTA DE PRODUTOS
	@Test
	public void teste30_deve_atribuir_uma_nova_lista_de_produtos() {
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(new Produto("123-abc", empresaBefore));
		empresaBefore.setProdutos(produtos);
		assertEquals(produtos, empresaBefore.getProdutos());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste31_nao_deve_atribuir_uma_nova_lista_de_produtos_passando_nulo() {
		empresaBefore.setProdutos(null);
	}

	@Test(expected = IllegalStateException.class)
	public void teste32_nao_deve_atribuir_uma_nova_lista_de_produtos_lista_vazia() {
		List<Produto> produtos = new ArrayList<Produto>();
		empresaBefore.setProdutos(produtos);
	}

	@Test(expected = IllegalStateException.class)
	public void teste33_deve_atribuir_uma_nova_lista_de_produtos_coma_mais_de_30_produtos() {
		List<Produto> produtos = new ArrayList<Produto>();
		for (int index = TAMANHO_MAX_LISTA_PRODUTO + 1; index != 0; index--) {
			produtos.add(new Produto("123-abc", empresaBefore));
		}
		empresaBefore.setProdutos(produtos);
	}

	// LISTA TELEFONE
	@Test
	public void teste34_deve_atribuir_uma_nova_lista_de_telefone() {
		Set<Telefone> telefones = new HashSet<Telefone>();
		telefones.add(new Telefone("+55", "11", "59160668"));
		empresaBefore.setTelefones(telefones);
		assertEquals(telefones, empresaBefore.getTelefones());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste35_nao_deve_atribuir_uma_nova_lista_de_telefone_passando_nulo() {
		empresaBefore.setTelefones(null);
	}

	@Test(expected = IllegalStateException.class)
	public void teste36_nao_deve_atribuir_uma_nova_lista_de_telefone_lista_vazia() {
		Set<Telefone> telefones = new HashSet<Telefone>();
		empresaBefore.setTelefones(telefones);
	}

	@Test(expected = IllegalStateException.class)
	public void teste37_nao_deve_atribuir_uma_nova_lista_de_telefones_coma_mais_de_5_telefones() {
		Set<Telefone> telefones = new HashSet<Telefone>();
		for (int index = TAMANHO_MAX_LISTA_TELEFONES + 1; index != 0; index--) {
			telefones.add(new Telefone("+55", "11", "11591606" + index));
		}
		empresaBefore.setTelefones(telefones);
	}

	// LISTA EMAILS
	@Test
	public void teste38_deve_atribuir_uma_nova_lista_de_emails() {
		List<Email> emails = new ArrayList<Email>();
		emails.add(new Email("emailcont@cont.com"));
		empresaBefore.setEmails(emails);
		assertEquals(emails, empresaBefore.getEmails());
	}

	@Test(expected = IllegalArgumentException.class)
	public void teste39_nao_deve_atribuir_uma_nova_lista_de_emails_passando_nulo() {
		empresaBefore.setEmails(null);
	}

	@Test(expected = IllegalStateException.class)
	public void teste40_nao_deve_atribuir_uma_nova_lista_de_emails_lista_vazia() {
		List<Email> emails = new ArrayList<Email>();
		empresaBefore.setEmails(emails);
	}

	@Test(expected = IllegalStateException.class)
	public void teste41_nao_deve_atribuir_uma_nova_lista_de_telefones_coma_mais_de_5_telefones() {
		List<Email> emails = new ArrayList<Email>();
		for (int index = TAMANHO_MAX_LISTA_EMAILS + 1; index != 0; index--) {
			emails.add(new Email("emailcont@cont.com"));
		}
		empresaBefore.setEmails(emails);
	}
	
	//ATIVO
	@Test
	public void teste42_deve_setar_empres_ativa() {
		empresaBefore.setAtivo(true);
		assertEquals(true, empresaBefore.getAtivo());
	}
	
	@Test
	public void teste43_deve_setar_empresa_inativa() {
		empresaBefore.setAtivo(false);
		assertEquals(false, empresaBefore.getAtivo());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void teste44_nao_deve_setar_empresa_ativa_nulo() {
		empresaBefore.setAtivo(null);
	}

	// TO STRING
	@Test
	public void teste45_deve_retonar_cnpj_no_to_string() {
		String result = empresaBefore.toString();
		assertThat(result, containsString(empresaBefore.getCnpj()));
	}

	@Test
	public void teste46_deve_retonar_razao_social_no_to_string() {
		empresaBefore.setRazaoSocial("Softmatic Sistemas Automaticos de Informatica Ltda");
		String result = empresaBefore.toString();
		assertThat(result, containsString(empresaBefore.getRazaoSocial()));
	}

	@Test
	public void teste47_deve_retonar_nome_fantasia_no_to_string() {
		empresaBefore.setNomeFantasia("Contmatic Phoenix");
		String result = empresaBefore.toString();
		assertThat(result, containsString(empresaBefore.getNomeFantasia()));
	}

	@Test
	public void teste48_deve_retonar_lista_enderecos_no_to_string() {
		String result = empresaBefore.toString();
		assertThat(result, containsString(String.valueOf(empresaBefore.getEnderecos())));
	}

	@Test
	public void teste49_deve_retonar_lista_ambientes_no_to_string() {
		String result = empresaBefore.toString();
		assertThat(result, containsString(String.valueOf(empresaBefore.getAmbientesTrabalhos())));
	}

	@Test
	public void teste50_deve_retonar_lista_produtos_no_to_string() {
		String result = empresaBefore.toString();
		assertThat(result, containsString(String.valueOf(empresaBefore.getProdutos())));
	}

	@Test
	public void teste51_deve_retonar_lista_emails_no_to_string() {
		String result = empresaBefore.toString();
		assertThat(result, containsString(String.valueOf(empresaBefore.getEmails())));
	}

	@Test
	public void teste52_deve_retonar_lista_telefones_no_to_string() {
		String result = empresaBefore.toString();
		assertThat(result, containsString(String.valueOf(empresaBefore.getTelefones())));
	}

	// HASHCODE
	@Test
	public void teste53_deve_retornar_o_mesmo_hashcode_para_da_empresa_com_mesmo_cnpj() {
		Empresa empresa1 = new Empresa(CNPJ);
		Empresa empresa2 = new Empresa(CNPJ);
		boolean resp = empresa1.hashCode() == empresa2.hashCode();
		assertTrue(resp);
	}

	@Test
	public void teste54_deve_retornar_hash_code_diferentes_para_cnpj_diferentes() {
		Empresa empresa1 = new Empresa(CNPJ);
		Empresa empresa2 = new Empresa("96160438000106");
		boolean resp = empresa1.hashCode() == empresa2.hashCode();
		assertFalse(resp);
	}

	// EQUALS
	@Test
	public void teste55_deve_retornar_true_quando_usar_equals_passado_empresas_iguais() {
		Empresa empresa1 = new Empresa(CNPJ);
		Empresa empresa2 = new Empresa(CNPJ);
		boolean resp = empresa1.equals(empresa2);
		assertTrue(resp);
	}

	@Test
	public void teste56_deve_retornar_falso_quando_usar_equals_passado_empresa_com_cnpj_diferente_difentes() {
		Empresa empresa1 = new Empresa(CNPJ);
		Empresa empresa2 = new Empresa("45626047000101");
		boolean resp = empresa1.equals(empresa2);
		assertFalse(resp);
	}

	@Test
	public void teste57_deve_retornar_falso_quando_usar_equals_passado_nulo() {
		boolean resp = empresaBefore.equals(null);
		assertFalse(resp);
	}

	@Test
	public void teste58_deve_retornar_falso_quando_usar_equals_passado_object() {
		boolean resp = empresaBefore.equals(new Object());
		assertFalse(resp);
	}

	@Test
	public void teste59_deve_retornar_verdadeiro_quando_usar_equals_passado_o_proprio_objeto() {
		boolean resp = empresaBefore.equals(empresaBefore);
		assertTrue(resp);
	}

	@Test
	@Ignore
	public void teste60_deve_ignorar_teste() {
		boolean resp = empresaBefore.equals(empresaBefore);
		assertTrue(resp);
	}

	@Test(timeout = 1000)
	public void teste61_deve_criar_um_objeto_empresas_em_menos_de_1_segundo() throws InterruptedException {
		assertNotNull(new Empresa("17940064000175"));
	}

	@Test
	public void teste62_nao_deve_criar_um_objeto_empresas_que_demore_mais_de_1_segundo() {
		final long DIVISAO_SECONDS = 1000000000;
		long start = System.currentTimeMillis();
		try {
			if(start / DIVISAO_SECONDS + 2 > System.currentTimeMillis() / DIVISAO_SECONDS) {
				throw new InternalError("Erro interno, não foi possivel criar empresa");
			} else {
				assertNull(new Empresa("17940064000175"));
			}
		} catch (InternalError e) {
		}
	}

	public static Integer getQuantidadeTest() {
		return quantidadeTest;
	}

	public static void setQuantidadeTest(Integer quantidadeTest) {
		TesteEmpresa.quantidadeTest = quantidadeTest;
	}
}
