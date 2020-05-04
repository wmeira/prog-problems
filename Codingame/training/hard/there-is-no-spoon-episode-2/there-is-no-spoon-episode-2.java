/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/hard/there-is-no-spoon-episode-2/
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Player {

	public static Map<Node, List<Node>> adjacencyList = new HashMap<Node, List<Node>>();

	public static List<Edge> edges = new ArrayList<Edge>();
	public static List<Edge> prohibitedEdges = new ArrayList<Edge>(); // 1 just 1, 2 just 2, 3 both prohibited
	public static int[][] grid;
	public static int width;
	public static int height;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		width = in.nextInt(); // the number of cells on the X axis
		in.nextLine();
		height = in.nextInt(); // the number of cells on the Y axis
		in.nextLine();
		grid = new int[height][width];
		// System.err.println(width + " " + height);

		for (int i = 0; i < height; i++) {
			String line = in.nextLine(); // width characters, each either a number or a '.'
			// System.err.println(line);
			char[] cs = line.toCharArray();
			for (int j = 0; j < cs.length; j++) {
				int value = charToInt(cs[j]);
				grid[i][j] = value;
				if (value != 0) {
					adjacencyList.put(new Node(j, i, value), new ArrayList<Node>());
				}
			}
		}
		generateAdjacencyList();

		// Eliminar ligações obrigatórias
		while (!allNodesConnected()) {
			insertObrigatoryEdges();
			if (!allNodesConnected()) { // still remaining connections -> make a decision
				boolean prohibitedEdgeFound = insertProhibitedEdges();
				if (!prohibitedEdgeFound) {
					break;
				}
			}
		}
		// printTable();
		if (!allNodesConnected()) {
			backtrackingSolution();
		}
		// System.err.println(edges.size() + ": " + edges);
		printEdges();
	}

	public static boolean backtrackingSolution() {
		List<Edge> allPossibleEdgesForAllNodes = getAllPossibleNewEdgesForAllNodes();
		// System.err.println(allPossibleEdgesForAllNodes.size() + " " + allPossibleEdgesForAllNodes);
		if (allPossibleEdgesForAllNodes.size() == 0) {
			if (allNodesConnected() && checkConnectivity()) {
				return true;
			} else {
				return false;
			}
		}

		for (Edge edge : allPossibleEdgesForAllNodes) {
			addEdge(edge);
			if (possibleTable()) {
				List<Edge> obrigatoryEdges = insertObrigatoryEdges();
				if (obrigatoryEdges.size() != 0) {
					if (backtrackingSolution()) {
						return true;
					} else {
						for (Edge oe : obrigatoryEdges) {
							removeEdge(oe);
						}
					}
				}

			}
			removeEdge(edge);
		}
		return false;
	}

	public static List<Edge> insertObrigatoryEdges() {
		List<Edge> totalObrigatoryEdges = new ArrayList<Edge>();
		boolean continueToFind = true;
		while (continueToFind) {
			continueToFind = false;
			for (Node n : adjacencyList.keySet()) {
				if (n.linksLeft > 0) {
					List<Edge> obrigatoryEdges = getObrigatoryEdges(n);
					if (obrigatoryEdges != null) {
						totalObrigatoryEdges.addAll(obrigatoryEdges);
						addEdges(obrigatoryEdges);
						continueToFind = true;
					}
				}
			}
		}
		return totalObrigatoryEdges;
	}

	public static boolean insertProhibitedEdges() {
		boolean prohibitedEdgeFound = false;
		outerloop: for (Node nodeToDecide : adjacencyList.keySet()) {
			if (nodeToDecide.linksLeft == 0)
				continue;

			List<Edge> edgesToDecide = getPossibleNewEdges(nodeToDecide);
			for (int i = 0; i < edgesToDecide.size(); i++) {
				addEdge(edgesToDecide.get(i));
				Edge edgeFromList = getEdge(edgesToDecide.get(i).a, edgesToDecide.get(i).b, edges);
				for (Node n : adjacencyList.keySet()) {
					if (!n.equals(nodeToDecide)) {
						int numberOfPossibleConnections = numberOfPossibleEdges(n);
						if (numberOfPossibleConnections < n.linksLeft) {
							Edge pEdge = getEdge(edgesToDecide.get(i).a, edgesToDecide.get(i).b, prohibitedEdges);
							if (pEdge == null) {
								prohibitedEdges.add(edgeFromList);
							} else {
								prohibitedEdges.remove(pEdge);
								Edge prohibitedEdge = new Edge(pEdge.a, pEdge.b);
								prohibitedEdge.connections = pEdge.connections + edgeFromList.connections;
								prohibitedEdges.add(prohibitedEdge);
								// pEdge.connections = pEdge.connections + edgeFromList.connections;
							}
							removeEdge(edgesToDecide.get(i));
							prohibitedEdgeFound = true;
							break outerloop;
						}
					}
				}
				removeEdge(edgesToDecide.get(i));
			}
		}
		return prohibitedEdgeFound;
	}

	public static boolean checkConnectivity() {
		int numberOfNodes = numberOfNodesConnected();
		return numberOfNodes == adjacencyList.keySet().size();
	}

	private static int numberOfNodesConnected() {
		List<Node> visited = new ArrayList<Node>();
		Node n = getFirstNode();
		visited.add(n);
		int numberOfNodes = nodes(n, visited);
		return numberOfNodes;
	}

	private static Node getFirstNode() {
		for (Node n : adjacencyList.keySet()) {
			return n;
		}
		return null;
	}

	private static int nodes(Node node, List<Node> visited) {
		int d = 0;
		for (Node n : adjacencyList.get(node)) {
			if (!visited.contains(n) && getEdge(node, n, edges) != null) {
				visited.add(n);
				d += nodes(n, visited);
			}
		}
		return d + 1;
	}

	public static void printTable() {
		for (Node n : adjacencyList.keySet()) {
			grid[n.y][n.x] = n.linksLeft;
		}

		System.err.println("Table: ");
		for (int y = 0; y < height; y++) {
			String s = "";
			for (int x = 0; x < width; x++) {
				s += grid[y][x] + " ";
			}
			System.err.println(s);
		}
	}

	public static Node getFirstNodeIncomplete() {
		for (Node n : adjacencyList.keySet()) {
			if (n.linksLeft > 0) {
				return n;
			}
		}
		return null;
	}

	public static void addEdges(List<Edge> edgesToAdd) {
		for (Edge edge : edgesToAdd) {
			addEdge(edge);
		}
	}

	public static boolean addEdge(Edge edge) {
		Edge edgeFromList = getEdge(edge.a, edge.b, edges);
		if (edgeFromList != null) {
			if (edgeFromList.connections == 2 || edge.a.linksLeft < 1 || edge.b.linksLeft < 1) {
				return false;
			}
			edgeFromList.connections++;
			edgeFromList.a.linksLeft--;
			edgeFromList.b.linksLeft--;
		} else {
			if (edge.a.linksLeft < edge.connections || edge.b.linksLeft < edge.connections) {
				return false;
			}
			edges.add(edge);
			edge.a.linksLeft = edge.a.linksLeft - edge.connections;
			edge.b.linksLeft = edge.b.linksLeft - edge.connections;
			;
		}
		return true;
	}

	public static void removeEdge(Edge edge) {
		Edge edgeFromList = getEdge(edge.a, edge.b, edges);
		if (edgeFromList != null) {
			if (edge.connections == 1) {
				if (edgeFromList.connections == 1) {
					edges.remove(edgeFromList);
				} else {
					edgeFromList.connections--;
				}
				edgeFromList.a.linksLeft++;
				edgeFromList.b.linksLeft++;
			} else if (edge.connections == 2) {
				edges.remove(edgeFromList);
				edgeFromList.a.linksLeft = edgeFromList.a.linksLeft + 2;
				edgeFromList.b.linksLeft = edgeFromList.b.linksLeft + 2;
			}
		}
	}

	public static Edge getEdge(Node a, Node b, List<Edge> edges) {
		for (Edge e : edges) {
			if (e.equals(new Edge(a, b))) {
				return e;
			}
		}
		return null;
	}

	public static boolean theEdgesCrossEachOther(Edge e1, Edge e2) {

		if (e1.a.x == e1.b.x && e2.a.y == e2.b.y) { // orthogonal
			if (e1.a.y != e2.a.y && e1.b.y != e2.a.y && e2.a.x != e1.a.x && e2.b.x != e1.a.x) { // not forming an L

				if (e2.a.y >= Math.min(e1.a.y, e1.b.y) && e2.a.y <= Math.max(e1.a.y, e1.b.y) && e1.a.x >= Math.min(e2.a.x, e2.b.x)
						&& e1.a.x <= Math.max(e2.a.x, e2.b.x)) { // crossing
					return true;
				}
			}
		} else if (e1.a.y == e1.b.y && e2.a.x == e2.b.x) { // orthogonal
			if (e2.a.y != e1.a.y && e2.b.y != e1.a.y && e1.a.x != e2.a.x && e1.b.x != e2.a.x) { // not forming an L

				if (e1.a.y >= Math.min(e2.a.y, e2.b.y) && e1.a.y <= Math.max(e2.a.y, e2.b.y) && e2.a.x >= Math.min(e1.a.x, e1.b.x)
						&& e2.a.x <= Math.max(e1.a.x, e1.b.x)) { // crossing
					return true;
				}
			}
		}
		return false;
	}

	public static boolean edgeCrossAnyOtherEdge(Edge edge) {
		for (Edge e : edges) {
			if (theEdgesCrossEachOther(edge, e)) {
				return true;
			}
		}
		return false;
	}

	public static boolean allNodesConnected() {
		for (Node n : adjacencyList.keySet()) {
			if (n.linksLeft != 0)
				return false;
		}
		return true;
	}

	public static List<Edge> getObrigatoryEdges(Node n) {
		List<Edge> possibleEdges = getPossibleNewEdges(n);
		if (numberOfConnections(possibleEdges) == n.linksLeft) {
			return possibleEdges;
		} else {
			return null;
		}
	}

	public static List<Edge> getAllPossibleNewEdgesForAllNodes() {
		List<Edge> allPossibleEdgesForAllNodes = new ArrayList<Edge>();
		for (Node n : adjacencyList.keySet()) {
			if (n.linksLeft > 0) {
				List<Edge> allPossibleEdgesForNode = getAllPossibleNewEdges(n);
				for (Edge edge : allPossibleEdgesForNode) {
					if (getEdgeWithConnections(edge.a, edge.b, edge.connections, allPossibleEdgesForAllNodes) == null) {
						allPossibleEdgesForAllNodes.add(edge);
					}
				}
			}
		}
		return allPossibleEdgesForAllNodes;
	}

	public static Edge getEdgeWithConnections(Node a, Node b, int connections, List<Edge> edges) {
		for (Edge e : edges) {
			if (e.equals(new Edge(a, b))) {
				if (e.connections == connections) {
					return e;
				}
			}
		}
		return null;
	}

	public static List<Edge> getAllPossibleNewEdges(Node n) {
		List<Edge> possibleEdges = getPossibleNewEdges(n);
		List<Edge> allPossibleEdges = new ArrayList<Edge>();
		for (Edge edge : possibleEdges) {
			if (edge.connections == 2) {
				Edge newEdge = new Edge(edge.a, edge.b);
				newEdge.connections = 1;
				allPossibleEdges.add(newEdge);
			}
			allPossibleEdges.add(edge);
		}
		return allPossibleEdges;
	}

	public static List<Edge> getPossibleNewEdges(Node n) {
		List<Edge> possibleEdges = new ArrayList<Edge>();
		for (Node adjacentNode : adjacencyList.get(n)) {
			if (adjacencyList.size() > 2 && n.totalLinks == 1 && adjacentNode.totalLinks == 1) {
				continue;
			}
			if (adjacentNode.linksLeft > 0 && n.linksLeft > 0) { // tem como ligar?
				// cruza outra aresta obrigatória?
				Edge newEdge = new Edge(n, adjacentNode);
				if (!edgeCrossAnyOtherEdge(newEdge)) {
					Edge existentEdge = getEdge(n, adjacentNode, edges);
					Edge prohibitedEdge = getEdge(n, adjacentNode, prohibitedEdges);
					// já tem dois links entre os dois ?
					if (existentEdge == null) {
						if (adjacencyList.size() > 2 && n.totalLinks == 2 && adjacentNode.totalLinks == 2) {
							if (prohibitedEdge == null) {
								newEdge.connections = 1;
								possibleEdges.add(newEdge);
							} else if (prohibitedEdge.connections == 2) {
								newEdge.connections = 1;
								possibleEdges.add(newEdge);
							}
						} else if (n.linksLeft > 1 && adjacentNode.linksLeft > 1) {
							if (prohibitedEdge == null) {
								newEdge.connections = 2;
								possibleEdges.add(newEdge);
							} else if (prohibitedEdge.connections == 2) {
								newEdge.connections = 1;
								possibleEdges.add(newEdge);
							} else if (prohibitedEdge.connections == 1) {
								newEdge.connections = 2;
								possibleEdges.add(newEdge);
							}
						} else if (n.linksLeft == 1 || adjacentNode.linksLeft == 1) {
							if (prohibitedEdge == null) {
								newEdge.connections = 1;
								possibleEdges.add(newEdge);
							} else if (prohibitedEdge.connections == 2) {
								newEdge.connections = 1;
								possibleEdges.add(newEdge);
							}
						}
					} else if (existentEdge.connections == 1) {
						if (prohibitedEdge == null) {
							newEdge.connections = 1;
							possibleEdges.add(newEdge);
						} else if (prohibitedEdge.connections == 1) {
							newEdge.connections = 1;
							possibleEdges.add(newEdge);
						}
					}
				}
			}
		}
		return possibleEdges;
	}

	public static int numberOfPossibleEdges(Node n) {
		List<Edge> possibleEdges = getPossibleNewEdges(n);
		return numberOfConnections(possibleEdges);
	}

	public static boolean possibleTable() {
		for (Node n : adjacencyList.keySet()) {
			if (numberOfPossibleEdges(n) < n.linksLeft) {
				return false;
			}
		}
		return true;
	}

	public static int numberOfConnections(List<Edge> edges) {
		int totalEdges = 0;
		for (Edge e : edges) {
			totalEdges += e.connections;
		}
		return totalEdges;
	}

	public static int charToInt(char c) {
		if (c == '.')
			return 0;
		else
			return Integer.parseInt("" + c);
	}

	public static Node getNode(int x, int y) {
		for (Node n : adjacencyList.keySet()) {
			if (n.x == x && n.y == y)
				return n;
		}
		return null;
	}

	public static void generateAdjacencyList() {
		for (Node node : adjacencyList.keySet()) {
			for (int i = node.x + 1; i < width; i++) {
				if (grid[node.y][i] > 0) {
					adjacencyList.get(node).add(getNode(i, node.y));
					break;
				}
			}
			for (int i = node.x - 1; i >= 0; i--) {
				if (grid[node.y][i] > 0) {
					adjacencyList.get(node).add(getNode(i, node.y));
					break;
				}
			}
			for (int i = node.y + 1; i < height; i++) {
				if (grid[i][node.x] > 0) {
					adjacencyList.get(node).add(getNode(node.x, i));
					break;
				}
			}
			for (int i = node.y - 1; i >= 0; i--) {
				if (grid[i][node.x] > 0) {
					adjacencyList.get(node).add(getNode(node.x, i));
					break;
				}
			}
		}
	}

	private static void printEdges() {
		for (Edge e : edges) {
			System.out.println(e.a.x + " " + e.a.y + " " + e.b.x + " " + e.b.y + " " + e.connections);
		}
	}
}

class Edge {
	Node a;
	Node b;
	int connections;

	Edge(Node a, Node b) {
		this.a = a;
		this.b = b;
		this.connections = 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;

		if (a == other.a && b == other.b)
			return true;
		if (a == other.b && b == other.a)
			return true;
		return false;
	}

	public String toString() {
		return a + " " + b + " " + connections;
	}
}

class Node {
	int x;
	int y;
	int linksLeft;
	int totalLinks;

	Node(int x, int y, int linksLeft) {
		this.x = x;
		this.y = y;
		this.linksLeft = linksLeft;
		this.totalLinks = linksLeft;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public String toString() {
		return "[" + x + " " + y + "]";
	}
}