Week 1 Union-Find & Analysis of Algorithms
-----
- Union-Find
    - Dynamic connectivity
    - Quick-find
        - use an array id[]
        - initialize:O(N), union:O(N), find:O(1)
        - do not scale
    _ Quick-union
        - id[i] is parent of i
        - initialize:O(N), union:O(N), find:O(N)
    - Improvement 1
        - weighting:avoid tall trees, keep track of *size* of each tree
        - union:O(lgN),find:O(lgN)
    - Improvement 2: path compression
        - after computing the root of p, set the id of each node to point to that node
        - 
    - Applications
        - percolation
- Analysis of algorithms

- Assignment
    - 在并查集中添加虚拟top结点，在isFull判断时由O(n) -> O(1)
    - 存在"backwash"bug