package com.senac.aplicacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.senac.estruturas.ListaDuplamenteEncadeada;
import com.senac.estruturas.Nodo;
import com.senac.modelos.Contato;

public class ManipularArquivo {
	
	public static Boolean salvar(Contato contato) {
		File arquivo = new File("agenda.txt");
		
		try(FileWriter fw = new FileWriter(arquivo, true)) {
			  BufferedWriter bw = new BufferedWriter(fw);            
			 
			  bw.write(contato.getNome());
			  System.out.println(contato.getNome());
			  bw.newLine();
			  bw.write(contato.getDdd().toString());
			  bw.newLine();
			  bw.write(contato.getTelefone().toString());
			  bw.flush(); 
		}catch(IOException ex){
			ex.printStackTrace();
		}
	
		
		return true;
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
			ListaDuplamenteEncadeada<Contato> agenda = new ListaDuplamenteEncadeada<Contato>();
			
			FileReader fr = null;
			
			try {
				fr = new FileReader(arquivo);
			} catch (FileNotFoundException e) {
				System.out.println("Não foi possível criar o arquivo!");
			}
			
			BufferedReader br = new BufferedReader(fr);
			
			String linha = null;
			Contato contato = new Contato();
			
			int i = 1;
			try {
				while((linha = br.readLine()) != null) {
					switch (i) {
					case 1:
						contato.setNome(linha);
						i = 2;
						break;
					case 2:
						contato.setDdd(Integer.parseInt(linha));
						i = 3;
						break;	
					case 3:
						contato.setTelefone(Integer.parseInt(linha));
						agenda.insert(new Nodo<Contato>(contato));
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
