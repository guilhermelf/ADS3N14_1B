package com.senac.aplicacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.senac.estruturas.ListaDuplamenteEncadeada;
import com.senac.estruturas.ListaOrdenada;
import com.senac.modelos.Contato;

public class ManipularArquivo {
	private static String nome;
	private static Integer telefone;
	
	public static Boolean salvar(Contato contato) {
		File arquivo = new File("agenda.txt");
		
		try(FileWriter fw = new FileWriter(arquivo, true)) {
			  BufferedWriter bw = new BufferedWriter(fw);            
			 
			  bw.write(contato.getNome());
			  bw.newLine();
			  bw.write(contato.getTelefone().toString());
			  bw.newLine();
			  bw.flush(); 
			  
			  bw.close();
			  fw.close();
			  
			  return true;
		}catch(IOException ex){
			System.out.println("Erro ao salvar no arquivo");
			return false;
		}		
	}
	
	
	public static ListaDuplamenteEncadeada<Contato> carregar() {
		if(!new File("agenda.txt").exists()) {
			File arquivo = new File("agenda.txt");
			
			try {
				arquivo.createNewFile();
			} catch (Exception e) {
				System.out.println("Não foi possivel continuar o programa!");
			}
			
			return new ListaDuplamenteEncadeada<Contato>();
		} else {
			File arquivo = new File("agenda.txt");
					
			FileReader fr = null;
			
			try {
				fr = new FileReader(arquivo);
			} catch (FileNotFoundException e) {
				System.out.println("Não foi possível criar o arquivo!");
			}
			
			ListaDuplamenteEncadeada<Contato> agenda = new ListaDuplamenteEncadeada<Contato>();
			
			BufferedReader br = new BufferedReader(fr);
			
			String linha = null;
			
			int i = 1;
			try {
				while((linha = br.readLine()) != null) {
					switch (i) {
					case 1:
						nome = linha;
						i = 2;
						break;
					case 2:
						telefone = Integer.parseInt(linha);
						agenda.insert(new Contato(nome, telefone));
						i = 1;
						break;
					default:
						break;
					}
				}
				
			} catch (Exception e) {
				System.out.println("Não foi possível recuperar os dados!");
			}
			
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return agenda;
		}
	}
	
	
}
