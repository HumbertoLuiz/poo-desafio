package io.github.humbertoluiz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import io.github.humbertoluiz.domain.Bootcamp;
import io.github.humbertoluiz.domain.Curso;
import io.github.humbertoluiz.domain.Dev;
import io.github.humbertoluiz.domain.Mentoria;

public class BootcampApp {

	private BufferedReader reader;

	public static void main(String[] args) throws Exception {
		BootcampApp bc = new BootcampApp();
		bc.reader = new BufferedReader(new InputStreamReader(System.in));
		bc.menu();
	}

	public void menu() throws Exception {
		String opcao = "";
		while (!opcao.equals("3")) {
			System.out.println("\n-----------OPCOES-------------\n");
			System.out.println("[1] Cadastrar");
			System.out.println("[2] Listar");
			System.out.println("[3] Sair");
			opcao = this.reader.readLine();

			if (opcao.equals("1")) {
				this.cadastrar();
			} else if (opcao.equals("2")) {
				this.listar();
			} else if (opcao.equals("3")) {
				System.out.println("Encerrando...");
			} else {
				System.out.println("Opcao invalida");
			}
		}
	}

	public void cadastrar() {
		try {
			Bootcamp bootcamp = new Bootcamp();
			System.out.println("Informe o Bootcamp: ");
			bootcamp.setNome(this.reader.readLine());
			System.out.println("Informe a Descrição: ");
			bootcamp.setDescricao(this.reader.readLine());

			System.out.println("\n----------------CURSOS----------------");

			Collection<Curso> listaDeCursos = new ArrayList<>();
			Curso curso = new Curso();

			System.out.println("\n----------------------------");

			System.out.println("Informe o título do Curso: ");
			curso.setTitulo(this.reader.readLine());

			System.out.println("Informe a Descrição:");
			curso.setDescricao(this.reader.readLine());

			System.out.println("Informe a Carga Horária:");
			String numCarga = this.reader.readLine();
			curso.setCargaHoraria(Integer.parseInt(numCarga));

			listaDeCursos.add(curso);

			bootcamp.getConteudos().add(curso);

			Collection<Mentoria> listaDeMentorias = new ArrayList<>();
			Mentoria mentoria = new Mentoria();

			System.out.println("\n----------------MENTORIAS----------------");

			System.out.println("\n----------------------------");

			System.out.println("Informe a mentoria: ");
			mentoria.setTitulo(this.reader.readLine());

			System.out.println("Informe a Descrição:");
			mentoria.setDescricao(this.reader.readLine());

			mentoria.setData(LocalDate.now());

			listaDeMentorias.add(mentoria);

			bootcamp.getConteudos().add(mentoria);

			Collection<Dev> listaDevs = new ArrayList<>();
			Dev dev = new Dev();

			System.out.println("\n----------------DEVS----------------");

			System.out.println("\n----------------------------");

			System.out.println("Informe o nome: ");
			dev.setNome(this.reader.readLine());
			dev.inscreverBootcamp(bootcamp);
			dev.progredir();
			dev.getConteudosInscritos();
			dev.getConteudosConcluidos();
			dev.calcularTotalXp();

			listaDevs.add(dev);

		} catch (Exception voo) {
			System.out.println("formato invalido...");
		}
	}

	public void listar() {
		System.out.println("\n--------------INSCRIÇÃO---------------\n");

		List<Dev> inscricao = new ArrayList<>();
		System.out.println("\n------------------------------------------------------------------");
		System.out.println("[Devs cadastrados]");
		System.out.println("--------------------------------------------------------------------");

		Iterator<Dev> iterator = inscricao.iterator();
		while (iterator.hasNext()) {
			Dev dev = iterator.next();

			System.out.println("Nome: " + dev.getNome()
			+ ", Conteudo: " + dev.getConteudosInscritos()
			+ ", Concluido: " + dev.getConteudosConcluidos()
			+ ", XP: " + dev.calcularTotalXp());
		}
	}
}
