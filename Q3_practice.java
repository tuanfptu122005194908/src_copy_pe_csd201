import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Queue;

/**
 *
 * @author Tuan va Quan
 */
/*
 * ||__TRAVERSAL__ // Các thuật toán duyệt đồ thị
 * || ||______breadth(boolean[], int, f) // Duyệt theo chiều rộng (Breadth-first
 * traversal)
 * || ||______breadth(int, f) // Duyệt BFS toàn đồ thị
 * || ||______fvisitDeg(int, f) // In đỉnh kèm bậc (A(4), E(3), ...)
 * || ||______depth(boolean[], int, f) // Duyệt theo chiều sâu (Depth-first
 * traversal)
 * || ||______depth(int, f) // Duyệt DFS toàn đồ thị
 * || ||______depth2(boolean[], int, int[], int, int, f) // Duyệt DFS, chỉ ghi
 * ra các đỉnh có thứ tự trong [from, to]
 * || ||______depth2(int, int, int, f) // Gọi depth2 chính (wrapper)
 * || ||______tinh bac cua tat ca cac dinh
 * || ||______calcDegree()
 * // Helper function to collect DFS order
 * || ||______maxDegreeVertex() // Tìm đỉnh có bậc lớn nhất
 * || ||______minDegreeVertex() // Tìm đỉnh có bậc nhỏ nhất
 * || ||______printAllVerticesWithDegree(f) // In tất cả đỉnh kèm bậc
 * || ||______ // Hàm in n đỉnh kèm bậc từ vị trí x đến y
 * // // HÀM DFS TỪ ĐỈNH i VÀ HIỂN THỊ n ĐỈNH TỪ VỊ TRÍ x ĐẾN y (KÈM BẬC)
 * || ||______depthCount(boolean[], int) // Đếm số đỉnh đã tham
 * || ||______countComponents() // Đếm số thành phần liên thông
 * || ||______shortestPath(int, int, f) // Tìm đường đi ngắn nhất từ đỉnh fro
 * đến
 * đỉnh to bằng BFS
 * || ||______printAdjacentVertices(int, f) // In các đỉnh kề của đỉnh k
 * || ||______countEdges() // Đếm số cạnh của đồ thị vô hướng
 * || ||______hasCycle() // Kiểm tra đồ thị có chu trình hay không
 * ||
 * ||__ALGORITHM__ // Các thuật toán đồ thị
 * || ||______dijkstra(int, int, f) // Dijkstra cơ bản – in đường đi và khoảng
 * cách
 * || ||______dijkstra2(int, int, f) // In 4 đỉnh cuối và đường đi ngắn nhất
 * || ||______dijkstra3(int, int, f) // Chỉ in ra đường đi ngắn nhất
 * || ||______eulerCycle(int, f) // Tìm và in chu trình Euler (Euler cycle)
 * || ||______EulerCycle(int, f) // Phiên bản khác của Euler cycle (dùng
 * MyStack)
 * || ||______checkEulerCycle(f) // Kiểm tra điều kiện tồn tại chu trình Euler
 * ||
 * ||__CHECK_FUNCTIONS__ // Các hàm kiểm tra tính chất đồ thị
 * || ||______deg(int) // Tính bậc của đỉnh
 * || ||______hasIsolated() // Kiểm tra có đỉnh cô lập
 * || ||______isConnected() // Kiểm tra đồ thị liên thông
 * || ||______isUnDirected() // Kiểm tra đồ thị vô hướng
 * || ||______allDegEven() // Kiểm tra tất cả đỉnh đều có bậc chẵn
 * || ||______hasEulerCycle() // Kiểm tra đồ thị có chu trình Euler
 * ||
 * ||__OTHER__ // Các hàm khác
 * || ||______connectedParts() // Đếm số thành phần liên thông
 */


 
(
// HÀM DFS TỪ ĐỈNH i VÀ HIỂN THỊ n ĐỈNH TỪ VỊ TRÍ x ĐẾN y (KÈM BẬC)
    // Perform DFS again to get the traversal order
    ArrayList<Integer> order = new ArrayList<>();
    boolean[] visited = new boolean[20];
    for (int i = 0; i < n; i++) {
        visited[i] = false;
    }
    
    // DFS from vertex 4 and collect order
    depthCollectOrder(visited, 4, order);
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            depthCollectOrder(visited, i, order);
        }
    }
    
    // Display 5 vertices with degrees from position 2 to 6 (index 1 to 5)
    for (int i = 1; i <= 5 && i < order.size(); i++) {
        int vertexIndex = order.get(i);
        fvisitDeg(vertexIndex, f);
    }


// Helper function to collect DFS order
void depthCollectOrder(boolean[] visited, int k, ArrayList<Integer> order) {
    order.add(k);
    visited[k] = true;
    for (int i = 0; i < n; i++) {
        if (!visited[i] && a[k][i] > 0) {
            depthCollectOrder(visited, i, order);
        }
    }

)








// // HÀM BFS TỪ ĐỈNH i VÀ HIỂN THỊ n ĐỈNH TỪ VỊ TRÍ x ĐẾN y (KÈM BẬC)
// Perform BFS again to get the traversal order

    ArrayList<Integer> order = new ArrayList<>();
    boolean[] visited = new boolean[20];
    for (int i = 0; i < n; i++) visited[i] = false;

    breadthCollectOrder(visited, start, order);
    for (int i = 0; i < n; i++) {
        if (!visited[i]) breadthCollectOrder(visited, i, order);
    }

    for (int i = x - 1; i < y && i < order.size(); i++) {
        int vertexIndex = order.get(i);
        fvisitDeg(vertexIndex, f);
    }


// helper function to collect BFS order
void breadthCollectOrder(boolean[] visited, int start, ArrayList<Integer> order) {
    Queue q = new Queue(); 
    q.enqueue(start);
    visited[start] = true;

    while (!q.isEmpty()) {
        int k = q.dequeue();
        order.add(k);
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                q.enqueue(i);
                visited[i] = true;
            }
        }
    }
}


// breadth-first traversal
void breadth(boolean[] visited, int k, RandomAccessFile f) throws Exception {
  GQueue q = new GQueue();
  int r, i;
  boolean[] enqueued = new boolean[20];
  for (i = 0; i < n; i++)
    enqueued[i] = false;
  q.enqueue(k);
  enqueued[k] = true;
  while (!q.isEmpty()) {
    r = q.dequeue();
    if (!visited[r]) {
      fvisit(r, f);// modifier function fvisit
      visited[r] = true;
    }
    for (i = 0; i < n; i++) {
      if (!visited[i] && !enqueued[i] && a[r][i] > 0) {
        q.enqueue(i);
        enqueued[i] = true;
      }
    }
  }
}

void breadth(int k, RandomAccessFile f) throws Exception {
  boolean[] visited = new boolean[20];
  int i;
  for (i = 0; i < n; i++)
    visited[i] = false;
  breadth(visited, k, f);
  for (i = 0; i < n; i++)
    if (!visited[i])
      breadth(visited, i, f);
}

// breadth first print degree with: A(4) E(3) F(3) G(2) I(3) B(2) C(1) H(2) D(1)
void fvisitDeg(int i, RandomAccessFile f) throws Exception {
  f.writeBytes(" " + v[i] + "(" + deg[i] + ")");
}

// dept-first traversal
void depth(boolean[] visited, int k, RandomAccessFile f) throws Exception {
  fvisit(k, f);
  visited[k] = true;
  for (int i = 0; i < n; i++) {
    if (!visited[i] && a[k][i] > 0)
      depth(visited, i, f);
  }
}

void depth(int k, RandomAccessFile f) throws Exception {
  boolean[] visited = new boolean[20];
  int i;
  for (i = 0; i < n; i++)
    visited[i] = false;
  depth(visited, k, f);
  for (i = 0; i < n; i++)
    if (!visited[i])
      depth(visited, i, f);
}

// tinh bac cua tat ca cac dinh
void calcDegree() {
  for (int i = 0; i < n; i++) {
    deg[i] = 0;
    for (int j = 0; j < n; j++) {
      if (a[i][j] > 0) {
        deg[i]++;
      }
    }
  }
}

// tim dinh co bac lon nhat
int maxDegreeVertex() {
  calcDegree();
  int maxIdx = 0;
  for (int i = 1; i < n; i++) {
    if (deg[i] > deg[maxIdx]) {
      maxIdx = i;
    }
  }
  return maxIdx;
}

// tim dinh co bac nho nhat
int minDegreeVertex() {
  calcDegree();
  int minIdx = 0;
  for (int i = 1; i < n; i++) {
    if (deg[i] < deg[minIdx]) {
      minIdx = i;
    }
  }
  return minIdx;
}

// in tat ca dinh kem bac
void printAllVerticesWithDegree(RandomAccessFile f) throws Exception {
  calcDegree();
  for (int i = 0; i < n; i++) {
    fvisitDeg(i, f);
  }
}

// in 5 dinh kem bac tu vi tri 2 den 6
// Hàm in n đỉnh kèm bậc từ vị trí x đến y
// order: danh sách thứ tự các đỉnh (từ DFS hoặc BFS)
// x, y: vị trí bắt đầu và kết thúc (index từ 0)
void printVerticesWithDegreeFromXToY(ArrayList<Integer> order, int x, int y, RandomAccessFile f) throws Exception {
  calcDegree(); // Tính bậc trước

  // Kiểm tra giới hạn
  int start = Math.max(0, x);
  int end = Math.min(order.size() - 1, y);

  for (int i = start; i <= end; i++) {
    int vertexIndex = order.get(i);
    fvisitDeg(vertexIndex, f);
  }
}

// ham dfs tu dinh i va hien thi n dinh tu vi tri x den y (kem bac)
void depthFromIndexShowRange(int startIndex, int x, int y, RandomAccessFile f) throws Exception {
  // DFS và lấy thứ tự duyệt
  ArrayList<Integer> order = depthFromIndex(startIndex, f);

  f.writeBytes("\r\n");

  // Hiển thị n đỉnh từ vị trí x đến y với bậc
  printVerticesWithDegreeFromXToY(order, x, y, f);
}

// Hàm DFS từ đỉnh i và hiển thị n đỉnh từ vị trí x đến y (CÓ BẬC)
void depthFromIndexShowRangeWithDegree(int startIndex, int x, int y, RandomAccessFile f) throws Exception {
  ArrayList<Integer> order = new ArrayList<>();
  boolean[] visited = new boolean[20];

  // Khởi tạo
  for (int i = 0; i < n; i++) {
    visited[i] = false;
  }

  // DFS từ đỉnh startIndex
  depth2(visited, startIndex, f, order);

  // Xử lý các đỉnh chưa thăm (nếu đồ thị không liên thông)
  for (int i = 0; i < n; i++) {
    if (!visited[i]) {
      depth2(visited, i, f, order);
    }
  }

  f.writeBytes("\r\n");

  // Hiển thị n đỉnh từ vị trí x đến y với bậc
  printVerticesWithDegreeFromXToY(order, x, y, f);
}

// Hàm in n đỉnh kèm bậc từ vị trí x đến y (không cần order - in theo thứ tự
// index)
void printVerticesWithDegreeRange(int x, int y, RandomAccessFile f) throws Exception {
  calcDegree(); // Tính bậc trước

  // Kiểm tra giới hạn
  int start = Math.max(0, x);
  int end = Math.min(n - 1, y);

  for (int i = start; i <= end; i++) {
    fvisitDeg(i, f);
  }
}

// dem so dinh da tham ham dfs
void depthCount(boolean[] visited, int k) {
  visited[k] = true;
  for (int i = 0; i < n; i++) {
    if (!visited[i] && a[k][i] > 0) {
      depthCount(visited, i);
    }
  }
}

// dem so thanh phan lien thong
int countComponents() {
  boolean[] visited = new boolean[20];
  int components = 0;

  for (int i = 0; i < n; i++) {
    if (!visited[i]) {
      depthCount(visited, i);
      components++;
    }
  }
  return components;
}

// tim duong di ngan nhat tu dinh fro den dinh to BFS
void shortestPath(int s, int t, RandomAccessFile f) throws Exception {
  int[] parent = new int[20];
  boolean[] visited = new boolean[20];
  Queue q = new Queue();

  for (int i = 0; i < n; i++) {
    parent[i] = -1;
    visited[i] = false;
  }

  q.enqueue(s);
  visited[s] = true;

  while (!q.isEmpty()) {
    int u = q.dequeue();
    if (u == t)
      break;

    for (int i = 0; i < n; i++) {
      if (!visited[i] && a[u][i] > 0) {
        visited[i] = true;
        parent[i] = u;
        q.enqueue(i);
      }
    }
  }

  // in duong di tu s den t
  if (!visited[t]) {
    f.writeBytes("No path from " + v[s] + " to " + v[t]);
  } else {
    // Truy vết đường đi
    ArrayList<Integer> path = new ArrayList<>();
    int current = t;
    while (current != -1) {
      path.add(0, current);
      current = parent[current];
    }

    f.writeBytes("Shortest path from " + v[s] + " to " + v[t] + ": ");
    for (int i = 0; i < path.size(); i++) {
      f.writeBytes(v[path.get(i)] + "");
      if (i < path.size() - 1)
        f.writeBytes(" -> ");
    }
  }
}

// ham tim va in cac dinh ke cua dinh k
void printAdjacentVertices(int k, RandomAccessFile f) throws Exception {
  f.writeBytes("Adjacent vertices of " + v[k] + ": ");
  boolean hasAdjacent = false;
  for (int i = 0; i < n; i++) {
    if (a[k][i] > 0) {
      fvisit(i, f);
      hasAdjacent = true;
    }
  }
  if (!hasAdjacent) {
    f.writeBytes("None");
  }
}

// ham dem so canh cua do thi vo huong
int countEdges() {
  int edges = 0;
  for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
      if (a[i][j] > 0) {
        edges++;
      }
    }
  }
  return edges;
}

// kiem tra do thi co chu trinh hay khong dfs
boolean hasCycle() {
  boolean[] visited = new boolean[20];
  for (int i = 0; i < n; i++) {
    if (!visited[i]) {
      if (dfsCycle(i, -1, visited)) {
        return true;
      }
    }
  }
  return false;
}

boolean dfsCycle(int u, int parent, boolean[] visited) {
  visited[u] = true;
  for (int v = 0; v < n; v++) {
    if (a[u][v] > 0) {
      if (!visited[v]) {
        if (dfsCycle(v, u, visited)) {
          return true;
        }
      } else if (v != parent) {
        return true;
      }
    }
  }
  return false;
}

// duyet depth-first nhung chi ghi ra cac dinh co thu tu nam trong khoang
// [from,to]
void depth2(boolean[] visited, int k, int[] counter, int from, int to, RandomAccessFile f) throws Exception {
  visited[k] = true;
  counter[0]++;
  if (counter[0] >= from && counter[0] <= to) {
    fvisit(k, f);
  }
  for (int i = 0; i < n; i++) {
    if (!visited[i] && a[k][i] > 0) {
      depth2(visited, i, counter, from, to, f);
    }
  }
}

void depth2(int k, int from, int to, RandomAccessFile f) throws Exception {
  boolean[] visited = new boolean[20];
  int[] counter = new int[1];
  counter[0] = 0;
  for (int i = 0; i < n; i++)
    visited[i] = false;
  depth2(visited, k, counter, from, to, f);
  for (int i = 0; i < n; i++)
    if (!visited[i])
      depth2(visited, i, counter, from, to, f);
}
// --> vi du: depth2(2, 3, 6, f);

// tim duong di ngan nhat tu dinh fro den dinh to
// in ra 4 dinh cuoi cung trong qua trinh tim duong di ngan nhat va do dai tu
// fro den cac dinh do
void dijkstra2(int fro, int to, RandomAccessFile f) throws Exception {
  boolean[] S = new boolean[n];
  int[] d = new int[n];
  int[] p = new int[n];
  int INF = 99;
  ArrayList<Integer> order = new ArrayList<Integer>();

  int i, j, k, t;
  for (i = 0; i < n; i++) {
    S[i] = false;
    d[i] = (a[fro][i] > 0) ? a[fro][i] : INF;
    p[i] = fro;
  }
  S[fro] = true;
  d[fro] = 0;
  order.add(fro);

  while (true) {
    t = INF;
    k = -1;
    for (i = 0; i < n; i++) {
      if (S[i])
        continue;
      if (d[i] < t) {
        t = d[i];
        k = i;
      }
    }
    if (k == -1)
      break;
    S[k] = true;
    order.add(k);
    if (k == to)
      break;
    for (i = 0; i < n; i++) {
      if (S[i])
        continue;
      if (a[k][i] > 0 && d[i] > d[k] + a[k][i]) {
        d[i] = d[k] + a[k][i];
        p[i] = k;
      }
    }
  }

  int start = Math.max(0, order.size() - 4);
  for (i = start; i < order.size(); i++) {
    int vertex = order.get(i);
    fvisit(vertex, f);
    f.writeBytes(":" + d[vertex]);
    if (i < order.size() - 1)
      f.writeBytes(" ");
  }
  f.writeBytes("\r\n");

  ArrayList<Integer> path = new ArrayList<Integer>();
  i = to;
  while (i != fro) {
    path.add(0, i);
    i = p[i];
  }
  path.add(0, fro);

  for (i = 0; i < path.size(); i++) {
    fvisit(path.get(i), f);
    if (i < path.size() - 1)
      f.writeBytes(" ");
  }
}

// tim duong di ngan nhat tu dinh fro den dinh to
// chi in ra duong di ngan nhat
void dijkstra3(int fro, int to, RandomAccessFile f) throws Exception {
  boolean[] S = new boolean[n];
  int[] d = new int[n];
  int[] p = new int[n];
  int INF = 99;

  int i, j, k, t;
  for (i = 0; i < n; i++) {
    S[i] = false;
    d[i] = (a[fro][i] > 0) ? a[fro][i] : INF;
    p[i] = fro;
  }
  S[fro] = true;
  d[fro] = 0;

  while (true) {
    t = INF;
    k = -1;
    for (i = 0; i < n; i++) {
      if (S[i])
        continue;
      if (d[i] < t) {
        t = d[i];
        k = i;
      }
    }
    if (k == -1)
      break;
    S[k] = true;
    if (k == to)
      break;
    for (i = 0; i < n; i++) {
      if (S[i])
        continue;
      if (a[k][i] > 0 && d[i] > d[k] + a[k][i]) {
        d[i] = d[k] + a[k][i];
        p[i] = k;
      }
    }
  }

  ArrayList<Integer> path = new ArrayList<Integer>();
  i = to;
  while (i != fro) {
    path.add(0, i);
    i = p[i];
  }
  path.add(0, fro);

  for (i = 0; i < path.size(); i++) {
    fvisit(path.get(i), f);
    if (i < path.size() - 1)
      f.writeBytes(" ");
  }
}

// dijkstra
void dijkstra(int fro, int to, RandomAccessFile f) throws IOException {
  boolean[] S = new boolean[n];
  int[] d = new int[n];
  int[] p = new int[n];
  int INF = 999;
  int i, j, k, t;
  for (i = 0; i < n; i++) {
    S[i] = false;
    d[i] = a[fro][i];
    p[i] = fro;
  }
  S[fro] = true;
  while (true) {
    // find d[k] = min {d[i}}
    t = INF;
    k = -1;
    for (i = 0; i < n; i++) {
      if (S[i] == true)
        continue;
      if (d[i] < t) {
        t = d[i];
        k = i;
      }
    }
    if (k == -1) {
      return;
    }
    // add k to S
    S[k] = true;
    if (k == to)
      break;
    // update d[i] & p[i]
    for (i = 0; i < n; i++) {
      if (S[i] == true)
        continue;
      if (d[i] > d[k] + a[k][i]) {
        d[i] = d[k] + a[k][i];
        p[i] = k;
      }
    }
  }
  // System.out.println("The shortest distance is " + d[to]);
  i = to;
  GStack s = new GStack();// store vertex
  ArrayList points = new ArrayList();// store distance
  while (true) {
    s.push(i);
    if (i == fro)
      break;
    i = p[i];
  }
  // print line 1: vertex, line2 : distance

  while (!s.isEmpty()) {
    i = s.pop();
    points.add(i);
  }
  for (int l = 0; l < points.size(); l++)
    f.writeBytes(v[l] + " ");// print vertex
  f.writeBytes("\n");
  for (int l = 0; l < points.size(); l++)
    f.writeBytes(d[(int) points.get(l)] + " ");// print distance
  f.writeBytes("\n");

  // print (0)A->(9)C->(2)F->(9)E
  for (int l = 0; l < points.size() - 1; l++)
    f.writeBytes("(" + d[(int) points.get(l)] + ")" + v[l] + " " + "->");
  f.writeBytes("(" + d[(int) points.get(points.size() - 1)] + ")" + v[points.size() - 1] + " ");
  f.writeBytes("\n");
}

// count degree
int deg(int i) {
  int s, j;
  s = 0;
  for (j = 0; j < n; j++)
    s += a[i][j];
  s += a[i][i];
  return (s);
}

// check has Isolated
boolean hasIsolated() {
  for (int i = 0; i < n; i++)
    if (deg(i) == 0)
      return (true);
  return (false);
}

// check connect
boolean isConnected() {
  boolean[] p = new boolean[n];
  int i, j, r;
  for (i = 0; i < n; i++)
    p[i] = false;
  GStack s = new GStack();
  s.push(0);
  p[0] = true;
  while (!s.isEmpty()) {
    r = s.pop();
    for (i = 0; i < n; i++)
      if (!p[i] && a[r][i] > 0) {
        s.push(i);
        p[i] = true;
      }
  }
  for (i = 0; i < n; i++)
    if (!p[i])
      return (false);
  return (true);
}

// check undirected
boolean isUnDirected() {
  int i, j;
  for (i = 0; i < n; i++)
    for (j = 0; j < n; j++)
      if (a[i][j] != a[j][i])
        return (false);
  return (true);
}

// check all deg even
boolean allDegEven() {
  for (int i = 0; i < n; i++)
    if (deg(i) % 2 == 1)
      return (false);
  return (true);
}

// check has euler cycle
boolean hasEulerCycle() {
  if (!hasIsolated() && isUnDirected() && isConnected() && allDegEven())
    return (true);
  else
    return (false);
}

// euler cycle
void eulerCycle(int fro, RandomAccessFile f) throws IOException {
  if (!hasEulerCycle()) {
    return;
  }
  int[] eu = new int[100];
  int m, i, j, r;
  GStack s = new GStack();
  s.push(fro);
  j = 0;
  while (!s.isEmpty()) {
    r = s.top();
    for (i = 0; i < n; i++) {
      if (a[r][i] > 0)
        break;
    }
    if (i == n) { // r is isolated
      s.pop();
      eu[j++] = r;

    } else {
      s.push(i);
      a[r][i]--;
      a[i][r]--;
    }
  }
  m = j;
  for (i = 0; i < m; i++) {
    f.writeBytes(v[eu[i]] + " ");
  }
}

/*
 * declare a stack S of characters (a vertex is labeled by a character)
 * declare an empty array E (which will contain Euler cycle)
 * push the vertex X to S
 * while(S is not empty)
 * {ch = top element of the stack S
 * if ch is isolated then remove it from the stack and put it to E
 * else
 * select the first vertex Y (by alphabet order), which is adjacent
 * to ch,push Y to S and remove the edge (ch,Y) from the graph
 * }
 * the last array E obtained is an Euler cycle of the graph
 */
void EulerCycle(int k, RandomAccessFile f) throws Exception {
  if (!isUndirected() || !isConnected() || !isEvenDegree()) {
    f.writeBytes("Conditions are not satisfied\r\n");
    return;
  }
  MyStack s = new MyStack();
  int[][] b = new int[20][20];
  int[] eu = new int[20];
  int m;
  int i, j, r;
  for (i = 0; i < n; i++)
    for (j = 0; j < n; j++)
      b[i][j] = a[i][j];
  s.push(k);// Dua dinh k vao Stack
  m = 0;// Ban dau chu trinh chua co phan tu nao
  while (!s.isEmpty()) {
    r = s.top();
    i = 0;
    while (i < n && b[r][i] == 0)
      i++;// Tim i dau tien de b[r][i]#0
    if (i == n) // r da la dinh co lap, dua r vao chu trinh Euler
    {
      eu[m++] = r;
      s.pop();
    } // Lay dinh co lap ra khoi Stack
    else {
      s.push(i);
      b[r][i]--;
      b[i][r]--;
    } // Loai canh (i,r) khoi do thi
  }
  // Display Euler cycle
  for (i = 0; i < m; i++)
    f.writeBytes(v[eu[i]] + "  ");
  f.writeBytes("\r\n");
}

// check conditions for the existence of Euler’s cycle
void checkEulerCycle(RandomAccessFile f) throws Exception {
  if (isUndirected())
    f.writeBytes("The graph is undirected.\r\n");
  else
    f.writeBytes("The graph is directed.\r\n");
  if (isConnected())
    f.writeBytes("The graph is connected.\r\n");
  else
    f.writeBytes("The graph is not connected.\r\n");

  if (isEvenDegree())
    f.writeBytes("All vertices have even degree.\r\n");
  else
    f.writeBytes("The graph has a vertex with odd degree.\r\n");
  if (isUndirected() && isConnected() && isEvenDegree())
    f.writeBytes("Conditions for Euler's cycle are satisfied.\r\n");
  else
    f.writeBytes("Conditions for Euler's cycle are not satisfied.\r\n");
}

// count the connectivity parts
public int connectedParts() { // f123.writeBytes("k = " + k + "\r\n");
  boolean[] pushed = new boolean[20];
  boolean cont = false;
  int i, j, k, r;
  for (i = 0; i < n; i++)
    pushed[i] = false;
  MyStack s = new MyStack();
  k = 0;
  while (true) {
    s.clear();
    i = 0;
    while (i < n && pushed[i])
      i++;
    if (i == n)
      break;
    s.push(i);
    pushed[i] = true;
    while (!s.isEmpty()) {
      r = s.pop();
      for (i = 0; i < n; i++) {
        if (i == r)
          continue;
        if (!pushed[i] && a[r][i] > 0) {
          s.push(i);
          pushed[i] = true;
        }
      }
    }
    k++;
  }
  return (k);
}
