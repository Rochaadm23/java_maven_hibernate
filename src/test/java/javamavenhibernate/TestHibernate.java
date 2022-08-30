package javamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
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
}
