package javamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TestHibernate {

	public void testeHibernateUtil() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();

		pessoa.setIdade(32);
		pessoa.setLogin("teste");
		pessoa.setNome("Fernando");
		pessoa.setSobreNome("Rocha");
		pessoa.setEmail("rsecinformation@gmail.com");
		pessoa.setSenha("123456");

		daoGeneric.salvar(pessoa);
		System.out.println(pessoa);
	}

	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(1L);

		pessoa = daoGeneric.pesquisar(pessoa);

		System.out.println(pessoa);
	}

	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);

		System.out.println(pessoa);
	}

	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);

		pessoa.setIdade(99);
		pessoa.setNome("Atualizaado Hibernate");

		pessoa = daoGeneric.updateMerge(pessoa);

		System.out.println(pessoa);
	}

	public void testeDelete() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGeneric.pesquisar(4L, UsuarioPessoa.class);

		daoGeneric.deletaPorId(pessoa);

		System.out.println(pessoa);
	}

	@Test
	public void testeListar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> pessoas = daoGeneric.listar(UsuarioPessoa.class);
		for (UsuarioPessoa usuarioPessoa : pessoas) {
			System.out.println(usuarioPessoa);
			System.out.println("------------------------------------------");

		}

	}

	@Test
	public void testeQueryList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<>();
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> pessoas = daoGeneric.getEntityManager()
				.createQuery("from UsuarioPessoa where nome = 'Atualizaado Hibernate'").getResultList();
		for (UsuarioPessoa p : pessoas) {
			System.out.println(p);
		}
	}

	@Test
	public void testeQueryListMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<>();
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> pessoas = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa order by id")
				.setMaxResults(2).getResultList();
		for (UsuarioPessoa p : pessoas) {
			System.out.println(p);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createQuery("from UsuarioPessoa where nome = :nome or sobrenome = :sobrenome")
				.setParameter("nome", "Fernando").setParameter("sobrenome", "Rocha").getResultList();

		for (UsuarioPessoa usuarioP : list) {
			System.out.println(usuarioP);
		}
	}

	@Test
	public void testeQuerySomaIdade() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<>();

		Double somaIdade = (Double) daoGeneric.getEntityManager()
				.createQuery("select avg(u.idade) from UsuarioPessoa u ")
				.getSingleResult();

		System.out.println("Soma de todas as Idades é --> " + somaIdade);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeNamedQuery1() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.findAll")
				.getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeNamedQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.findName")
				.setParameter("nome", "Fernando")
				.getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeGravaTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric<>();
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(4L, UsuarioPessoa.class);

		TelefoneUser telefone = new TelefoneUser();
		telefone.setTipo("Celular");
		telefone.setNumero("21985684586");
		telefone.setUsuarioPessoa(pessoa);

		daoGeneric.salvar(telefone);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeConsultaTelefones() {
		DaoGeneric daoGeneric = new DaoGeneric<>();
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(4L, UsuarioPessoa.class);
		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
			System.out.println("Telefone numero: " + fone.getNumero());
			System.out.println("Tipo telefone: " + fone.getTipo());
			System.out.println("Usuário: " + fone.getUsuarioPessoa().getNome());
			System.out.println("-----------------------------------------------------");
		}
	}

}
