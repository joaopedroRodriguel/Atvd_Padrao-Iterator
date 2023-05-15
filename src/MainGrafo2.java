

import java.io.IOException;

import grafo.dirigido.*;
import model.Aluno;

public class MainGrafo2 {

	 public static void main(String args[]){
		 // Teste == 1: Testando apenas a criação de vertices com objetos Aluno, arestas e o grafo, isolados.
		 // Teste == 2: Testando o Generics com String. Testando o subgrafo
		 // Teste == 3: Testando algoritmos de percurso/travessia
		 int Teste = 3; // Configure com os valores 1, 2, 3 ou 4
		 
		 if (Teste == 1) {
			 // Testando apenas a criação de vertices, arestas e o grafo, isolados.
			 Vertice<String> v1 = new Vertice<String>("alex");
			 Vertice<Aluno> v2 = new Vertice<Aluno>(new Aluno("Nathan",123));
			 Vertice<Aluno> v3 = new Vertice<Aluno>(new Aluno("Matheus",456));
			 System.out.println(v1);
			 System.out.println(v2);
			 
			 
			 Grafo<Aluno> g = new Grafo<Aluno>();
			 Aluno a1 = new Aluno("alex",111);
			 Aluno a2 = new Aluno("dan",222);
			 Aluno a3 = new Aluno("nathan",333);
			 
			 Aresta<Aluno> aresta1 = new Aresta<Aluno>(v2,v3,0);
			 System.out.println(aresta1);
			 			
			 g.addVertice(a1);
			 g.addVertice(a2);
			 g.addVertice(a3);
			 g.addAresta(a1, a2, 0);
			 System.out.println(g);
			
		 } else if (Teste == 2) {
			 // Testando o Generics com String. Testando o subgrafo
			 Grafo<String> g = new Grafo<String>();
			 Grafo<String> sub = null;
			 
			 g.addVertice("alex");
			 g.addVertice("Nathan");
			 g.addVertice("dan");
			 g.addVertice("duda");
			 g.addAresta("alex", "Nathan", 1);
			 g.addAresta("alex", "dan", 1);
			 g.addAresta("dan", "duda", 1);
			 
			 g.addVertice("alice");
			 g.addVertice("alessandra");
			 g.addAresta("alice", "alessandra", 1);	 
			
			 System.out.println(g);
			 
			 sub = g.getSubGrafo("alex");
			 
			 System.out.println(sub);
		
		 } else if (Teste == 3) {
			 // Testando algoritmos de percurso/travessia

			 Grafo<String> g = new Grafo<String>();

			 g.addVertice("alex");
			 g.addVertice("Nathan");
			 g.addVertice("dan");
			 g.addVertice("duda");
			 g.addAresta("alex", "Nathan", 1);
			 g.addAresta("alex", "dan", 1);
			 g.addAresta("dan", "duda", 1);

			 System.out.println("Grafo:");
			 System.out.println(g);

			 System.out.println("Iteração BFS:");
			 BFSIterator<String> bfsIterator = new BFSIterator<String>(g);
			 while (bfsIterator.hasNext()) {
				 Vertice<String> v = bfsIterator.next();
				 System.out.println(v);
			 }

			 System.out.println("Iteração DFS:");
			 DFSIterator<String> dfsIterator = new DFSIterator<String>(g);
			 while (dfsIterator.hasNext()) {
				 Vertice<String> v = dfsIterator.next();
				 System.out.println(v);
			 }
		 }
	 }
	
}
