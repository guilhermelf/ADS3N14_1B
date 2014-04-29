package com.guilhermelf.AgendaTelefonica;

import com.guilhermelf.AgendaTelefonica.controller.AgendaTelefonicaController;
import com.guilhermelf.AgendaTelefonica.view.ConsoleView;

public class AgendaTelefonica {
	public static void main(String[] args) {
		ConsoleView view = new ConsoleView();
		AgendaTelefonicaController controller = new AgendaTelefonicaController(view);
		String command = "";
		
		controller.loadFile("agenda.dat");
		view.message("Digite 'ajuda' para comandos validos.");
		while (!command.equals("sair")) {
			command = view.read("Comando").toLowerCase();
			if (command.equals("mostrar"))
				controller.showContato();
			else if (command.equals("avancar"))
				controller.nextContato();
			else if (command.equals("voltar"))
				controller.previousContato();
			else if (command.equals("inserir"))
				controller.insert();
			else if (command.equals("infix"))
				controller.infix();
			else if (command.equals("prefix"))
				controller.prefix();
			else if (command.equals("postfix"))
				controller.postfix();
			else if (command.equals("excluir"))
				controller.delete();
			else if (command.equals("profundidade"))
				controller.profundidade();
			else if (command.equals("largura"))
				controller.largura();
			else if (command.equals("ajuda")) {
				view.message("ajuda    \t mostra as opcoes disponiveis da aplicacao\n" +
						"avancar    \t avanca ao proximo contato (ordem alfabetica)\n" +
						"excluir    \t exclui um contato da arvore\n" +
						"infix    \t imprime os elementos, percorrendo a arvore  por Travessia Infixa (ordenado)\n" +
						"inserir    \t insere contato na agenda (nome e telefone)\n" +
						"largura   \t busca um contato na arvore por largura \n" +
						"mostrar    \t exibe o contato atual\n" +
						"postfix    \t imprime os elementos, percorrendo a arvore por Travessia Posfixa \n" +
						"prefix    \t imprime os elementos, percorrendo a arvore por Travessia Prefixa \n" +
						"profundidade   \t busca um contato na arvore por profundidade \n" +
						"sair    \t encerra a aplicacao\n" +
						"voltar     \t volta ao contato anterior (ordem alfabetica)"
				);
			} else if(!command.equals("sair")) 
				view.message("Comando não encontrado, digite 'ajuda' para ver os comandos validos.");			
		}
		controller.saveFile("agenda.dat");
	}
}
