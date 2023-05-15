package grafo.dirigido;


import java.util.*;


/**
 * Classe que disponibiliza as funcionalidades de um grafo direcionado.
 * @author Alex
 * Data de criação: 06/06/2013
 * Atualização usando Generics: 24/03/2023
 */
public class Grafo<T> implements Iterable<Vertice<T>>{
	
	public static final int INFINITY = 99999;
	
	/** Colecao de vertices do grafo */	 
	private List<Vertice<T>> vertices;
	private List<Aresta<T>> arestas;

	
	public Grafo() {
		vertices = new ArrayList<Vertice<T>>();
		arestas = new ArrayList<Aresta<T>>();
	}

	@Override
	public Iterator<Vertice<T>> iterator() {
		return new BFSIterator<T>(this);
	}

	public Iterator<Vertice<T>> dfsIterator() {
		return new DFSIterator<T>(this);
	}


	/** Obtem um List de Vertices do grafo
	 * @return the vertices
	 */
	public List<Vertice<T>> getVertices() {
		return vertices;
	}

	
	/** 
	 * Adiciona um vertice ao grafo. 
	 * Se o Vertice ja existir (pelo nome), o objeto nao � criado. Caso contrario, um 
	 * novo objeto do tipo Vertice � criado e retornado.
	 * @param //nome - Um String que representa o conteudo do vertice
	 * @return O vertice criado
	 */
	public Vertice<T> addVertice(T carga) {
		Vertice<T> v;
		if ( (v = (Vertice<T>) getVertice( carga )) == null ) {
			v = new Vertice<>(carga);
			vertices.add(v);
		}
		return v;
	}

	/**
	 * M�todo que localiza e retorna a referencia para um vertice existente no grafo.
	 * @param //nome O conteudo para identificacao do vertice
	 * @return A referencia para o vertice (quando existente) ou null.
	 */
	public Vertice<T> getVertice( T carga){

        for (Vertice<T> u : vertices) {
        	if ( u.getCarga().equals( carga ))
        		return u;
        }
        return null;
	}

	/**
	 * Verifica a existencia de um vertice no grafo.
	 * @param v - O vertice desejado
	 * @return <b>true</b>, caso o vertice perten�a ao grafo, ou <b>false</b> caso contrario.
	 */
	public boolean exists(Vertice<T> v){

        for (Vertice<T> u : vertices) {
        	if ( u.getCarga().equals( v.getCarga() ) )
        		return true;
        }
        return false;
	}

	/**
	 * Obtem o n�mero de vertices incidentes ao vertice u.
	 * @param u O vertice o qual se deseja obter a quantidade de incidentes
	 * @return Um numero inteiro que quantifica o numero de vertices incidentes.
	 */
	 public int getNumIncidentes(Vertice<T> u){
		int count=0;
		for(Aresta<T> arco: arestas){
			if( arco.getDestino().equals(u))
				count++;
		}
		return count;
	 }

	 

	@SuppressWarnings("unchecked")
	public List<Vertice<T>> incidentes( Vertice<T> u ){
		 List<Vertice<T>> vertex = new ArrayList<Vertice<T>>();
		 for(Aresta<T> arco: arestas){
			if( arco.getDestino().equals(u))
				vertex.add((Vertice<T>) arco.getOrigem());
			else if( arco.getOrigem().equals(u))
				vertex.add((Vertice<T>) arco.getDestino());
					
		}
		return vertex;
	 }
	 
	 
	
	/**
	 * Adiciona uma aresta ao grafo.
	 * O m�todo se encarreda de adcionar a aresta como ajacente ao vertice de origem.
	 * @param origem - String que identifica o vertice de origem
	 * @param destino - String que identifica o vertice de destino
	 * @param peso - O peso da aresta
	 * @return Uma referencia para a aresta criada caso os vertices ja existam. Caso contrario,
	 * retorna null.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Aresta<T> addAresta(T origem, T destino, int peso) {
		Vertice<T> u,v;
		Aresta<T> arco=null;
		if ( (u=getVertice(origem))==null || (v=getVertice(destino))==null )
			return arco;
	
		return (Aresta<T>) addAresta(u,v,peso);
	}
	
	/**
	 * Adiciona uma aresta ao grafo.
	 * @param origem - O objeto vertice de origem
	 * @param destino - O objeto vertice de destino
	 * @param peso - o peso da aresta
	 * @return Uma referencia para a aresta criada caso os vertices ja existam. Caso contrario,
	 * retorna null.
	 */
	@SuppressWarnings("unchecked")
	public Aresta<T> addAresta(Vertice<T> origem, Vertice<T> destino, int peso) {
		Aresta<T> e; 
		/* Se a aresta j� existir, inclusive com o mesmo peso, n�o cria  */
		if( (e= (Aresta<T>) getAresta(origem,destino))==null ) {

			e = new Aresta<T>(origem, destino, peso);
			origem.addAdj(e);
			arestas.add(e);

		} 
		return e;
	}
	
	/**
	 * Obtem a aresta espec�fica para um determinado par de vertices origem/destino.
	 * @param origem - O v�rtice de origem
	 * @param destino - O v�rtice de destino
	 * @return Uma referencia para a aresta criada caso os vertices ja existam. Caso contrario,
	 * retorna null
	 */
	public Aresta<T> getAresta( Vertice<T> origem, Vertice<T> destino ){
		Aresta<T> arc = null;
		for(int i=0 ; i < arestas.size(); i++ ){
			arc = arestas.get(i);
			if ( arc.getOrigem().equals( origem) && arc.getDestino().equals( destino) )
				return arc;
		}
		return null;
	}
	
	/**
	 * Obtem a colecao de arestas do grafo.
	 * @return As arestas.
	 */
	public List<Aresta<T>> getArestas() {
		return arestas;
	}



	/**
	 * Verifica se o grafo est� vazio.
	 * @return true - se o grafo est� vazio, false caso contr�rio.
	 */
	public boolean isEmpty(){
		return vertices.size() == 0;
	}

	/**
	 * Esvazia o grafo.
	 */
	 public void clear(){
		 vertices.clear();
		 arestas.clear();
	 }
	 
	@SuppressWarnings("unchecked")
	public String toString() {
        String r = "";
        for (Vertice<T> u : vertices) {
        	if( u.getAdj().size()!= 0 ) {
        		r += u.getCarga().toString() + " -> ";
        	} else
        		continue;
        	
            for (Aresta<T> e : u.getAdj()) {
//            	r += u.tag + " -> ";
				Vertice<T> v = (Vertice<T>) e.getDestino();
                r += v.getCarga().toString() + ", ";
            }
            r += "\n";
        }
        return r;
    }

	
	private void showMarked(){
		for(int i=0; i < vertices.size(); i++ ){
			if ( vertices.get(i).getStatus() == VertexState.Visited )
				System.out.print( vertices.get(i).getCarga());
		}

	}
	

	/**
	 * Implementacao do algoritmo BFS (Busca em Profundidade) em um grafo direcionado,
	 * para obter a menor distancia de um vertice aos demais.
	 * O uso de BFS � apropriado neste caso, pois cada aresta vai ser contada apenas 1 vez.
	 * Nao necessita de peso pra utilizar Dijkstra, como foi feito anteriormente.
	 * @param source O vertice inicial
	 */
	public void BFSDistance( T source ){
		
		Vertice<T> v = getVertice(source);
		if( !exists( v ) )
			return;
		
		// Marcando todos os n�s como NAO-VISITADOS
		for(int i=0; i < vertices.size(); i++ ){
			vertices.get(i).setStatus(VertexState.Unvisited);
			vertices.get(i).setDistance(INFINITY); // valor muito alto
		}

		Queue<Vertice<T>> q = new LinkedList<Vertice<T>>();
		
		v.setStatus(VertexState.Visited);
		v.setDistance(0);
		q.add(v);
		
		while ( !q.isEmpty()){
			Vertice<T> u = q.remove();
						
			for(Aresta arco: u.getAdj() ) {
				@SuppressWarnings("unchecked")
				Vertice<T> w = (Vertice<T>) arco.getDestino();
				
				if( w.getStatus() == VertexState.Unvisited ) {							
					w.setStatus(VertexState.Visited);
					q.add(w);
					w.setDistance(u.getDistance() + 1);
				}
			}
			
			u.setStatus(VertexState.Finished);
		}
		
		System.out.println("Vertice (s)= " + source + "\n");
		for(int i=0; i < vertices.size(); i++ ){
			if( vertices.get(i).getDistance() == INFINITY )
				continue;
			System.out.print("(v)=" + vertices.get(i).getCarga());
			System.out.println(", dist(v)="  +  vertices.get(i).getDistance());
		}


	}

	
	/**
	 * Implementacao do algoritmo BFS (Busca em Profundidade)
	 * OBS: est� considerando um grafo DIRECIONADO.
	 * @param source O vertice inicial
	 */
	public void BFS( T source ){
		Queue<Vertice<T>> q = new LinkedList<Vertice<T>>();
		List<Vertice<T>> uAdjacentes = null;
		
		Vertice<T> v = getVertice(source);
					
		if( !exists( v) )
			return;
		// Marcando todos os n�s como NAO-VISITADOS
		for(int i=0; i < vertices.size(); i++ ){
			vertices.get(i).setStatus(VertexState.Unvisited);
		}
		
		
		v.setStatus(VertexState.Visited);
		q.add(v);
		showMarked();
	
		while ( !q.isEmpty()){
			Vertice<T> u = q.remove();
			System.out.print("\t" + q.toString() + "\n");
			
			uAdjacentes = incidentes(u);
			
			for(Vertice<T> w: uAdjacentes){
				
				if( w.getStatus() == VertexState.Unvisited ) {							
					w.setStatus(VertexState.Visited);
					q.add(w);								
				}
				showMarked();
				System.out.print("\t" + q.toString() + "\n");
			}
			
			u.setStatus(VertexState.Finished);
		}

	}

	/**
	 * Efetua um percurso em profundidade para evitar rela��es de multiplas-instancias, na qual
	 * � feita uma poda no grafo para restringi-lo a uma arvore, de forma que cada vertice s�
	 * tenha um pai. Para isso, o algoritmo DFS() foi modificado para efetuar a tarefa.
	 * @return O grafo modificado, sem as relacoes de multiplas-instancias.
	 */
	
	public Grafo<T> DFS(){
		Grafo<T> Sii = new Grafo<T>();
		
		for(int i=0; i < vertices.size(); i++ ){
			vertices.get(i).setStatus(VertexState.Unvisited);
		}
		
		
		for( Vertice<T> u : vertices){
			runDFS( u, Sii );
		}
		
		return Sii;
		
	}
	
	//* Deep-First-Search (DFS): Busca em Profundidade */
	/**
	 * Efetua um percurso em profundidade, baseado no algoritmo Deep-First-Search(DFS).
	 * Este m�todo faz o percurso utilizando um vertice inicial, at� o vertice n que esteja
	 * na rede do v�rtice inicial. 
	 * OBS: ESTE ALGORITMO � MATRIZ, o qual deve ser usado como c�digo inicial para adequar
	 * �s necessidades de cada problema.
	 * 
	 * @param //tag - String que identifica o vertice de partida.
	 */
	public void DFS(T source){
		Vertice<T> u = null;
				
		if ((u = getVertice(source)) == null) {
			System.err.println("vertice nao encontrado em runDFS()");
			return;
		}
		
		for(int i=0; i < vertices.size(); i++ ){
			vertices.get(i).setStatus(VertexState.Unvisited);
		}
		
		runDFS( u );
		
	}
	
	

	@SuppressWarnings("unchecked")
	private void runDFS(Vertice<T> u, Grafo<T> grafo){
		Vertice<T> w = null;
		List<Aresta<T>> uAdjacentes = null;
				
		u.setStatus(VertexState.Visited);
		grafo.addVertice( u.getCarga());
		
		uAdjacentes = u.getAdj();
			
		for(Aresta<T> arco: uAdjacentes){
			w = (Vertice<T>) arco.getDestino();
			
			if( w.getStatus() == VertexState.Unvisited ) {							
				runDFS(w,grafo);
				grafo.addAresta(  arco.getOrigem(), arco.getDestino(), arco.getPeso());								
			}
		} 
		
		u.setStatus(VertexState.Finished);
	}

	
	/**
	 * M�todo secund�rio, que efetua realmente o percurso no grafo a partir de um 
	 * vertice espec�fico
	 * OBS: ESTE ALGORITMO � MATRIZ, o qual deve ser usado como c�digo inicial para adequar
	 * �s necessidades de cada problema.
	 * DEPENDENCIA: s� pode ser chamado a partir de DFS();
	 * @param u - O vertice de busca.
	 */
	@SuppressWarnings("unchecked")
	private void runDFS(Vertice<T> u){
		Vertice<T> w = null;
		List<Aresta<T>> uAdjacentes = null;
				
		u.setStatus(VertexState.Visited);
		System.out.print("\t" + u.toString() + "\n");
		
		uAdjacentes = u.getAdj();
		
		for(Aresta<T> arco: uAdjacentes){
			w = (Vertice<T>) arco.getDestino();
			if( w.getStatus() == VertexState.Unvisited )
				runDFS(w);
		}
		
		u.setStatus(VertexState.Finished);
	}
	
	
	/**
	 * @return the list of all valid destinations from the given city.
	 */
	@SuppressWarnings("unchecked")
	public List<Vertice<T>> getDestinations(Vertice<T> v)
	{
		
		List<Vertice<T>> list = new ArrayList<Vertice<T>>();
		List<Aresta<T>> arcos = v.getAdj();
				
		for( Aresta<T> a : arcos){
			list.add( (Vertice<T>) a.getDestino() );
	
		}
		
		return list;
	}
	
	public int getDistance(Vertice<T> start, Vertice<T> end)
	{
		List<Aresta<T>> arcos = start.getAdj();
		
		for( Aresta<T> a : arcos){
			if( a.getDestino().getCarga().equals( end.getCarga()) )
				return a.getPeso();
	
		}
		return 0 ;
	}
	
	 @SuppressWarnings("unchecked")
	public Object clone() {
//	        try {
//	            return (Grafo)super.clone();
//	        }
//	        catch (CloneNotSupportedException e) {
//	            return null;
//	        }
		 
		 try {
			Grafo<T> clone = (Grafo<T>)super.clone();
			
			   //Clona o resto. 
			clone.arestas.addAll(arestas);
			clone.vertices.addAll(vertices);
	        return clone;          
		} catch (CloneNotSupportedException e) {

			e.printStackTrace();
			return null;
		} // Clona os tipos primitivos;

	 }
	 

	
	
	@SuppressWarnings("unchecked")
	public Grafo<T> getSubGrafo(T carga){
		Vertice<T> source,target, element;
		
		if( (source=getVertice(carga))==null )
			return null;
		Grafo<T> grafo = new Grafo<T>();
		
		Queue<Vertice<T>> fila = new LinkedList<Vertice<T>>(); 
		fila.add(source);
		
		
		while( !fila.isEmpty() ) {
			element = fila.poll();
			source = grafo.addVertice( element.getCarga());
			
			for(Aresta edge: element.getAdj()){
				target = grafo.addVertice( (T) edge.getDestino().getCarga());
				grafo.addAresta(source, target, edge.getPeso());
				fila.add((Vertice<T>) edge.getDestino());				
			}
		
		}
		
		return grafo;
	}

}
