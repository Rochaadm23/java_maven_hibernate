package javamavenhibernate;

import org.junit.Test;

import dao.DaoGeneric;
import model.UsuarioPessoa;

public class TestHibernate {

	@Test
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
	}
}
