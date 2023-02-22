package com.mycompany.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.io.File;
import java.io.IOException;

public class BFS {

    public static void main(String[] args) throws FileNotFoundException {
        int N, e, src, des;
        File file = new File("/home/cse/Documents/Input2");
        Scanner sc = new Scanner(file);
        Scanner sp = new Scanner(System.in);
        N = sc.nextInt();
        e = sc.nextInt();
        int parent[] = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = -1;
        }
        int graph[][] = new int[N][N];
        for (int i = 0; i < e; i++) {
            src = sc.nextInt();
            des = sc.nextInt();
            graph[src][des] = 1;
        }
        int vis[], lev[];
        vis = new int[N];
        lev = new int[N];

        for (int i = 0; i < N; i++) {
            vis[i] = 0;
            lev[i] = 999999;
        }
        int s = 0;
        vis[s] = 1;
        lev[s] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < N; v++) {
                if (graph[u][v] == 1 && vis[v] == 0) {
                    vis[v] = 1;
                    lev[v] = lev[u] + 1;
                    parent[v] = u;
                    q.add(v);
                }
            }
            vis[u] = 2;
        }
        for (int i = 0; i < N; i++) {
            System.out.println("Node = " + i + " Level = " + lev[i]);
        }
        System.out.println("Enter source : ");
        int sd = sp.nextInt();
        System.out.println("Enter destination : ");
        int dd = sp.nextInt();
        if (Tracker(parent, sd, dd)) {
            path(parent, sd, dd);
        } else {
            System.out.println("No path!");
        }
    }

    static void path(int parent[], int s, int d) {
        if (d != s) {
            path(parent, s, parent[d]);
        }
        System.out.print(d);
    }

    static boolean Tracker(int parent[], int s, int d) {
        if (parent[d] != -1) {
            return Tracker(parent, s, parent[d]);
        }
        if (d == s) {
            return true;
        } else {
            return false;
        }
    }
}
