2020年1月6日15:40:05

http://projectreactor.mydoc.io/?t=44473
https://www.cs.usfca.edu/~galles/visualization/Algorithms.html  
讲解： https://www.cnblogs.com/onepixel/p/7674659.html     
视图演示：https://visualgo.net/zh/

当前各种算法对于10万数据耗时

| 类型                 | 异步执行8次(ms)  | 异步执行16次(ms) | 同步执行16次(ms) | 同步执行16次(ms) |
|--------------------|-------------|-------------|-------------|-------------|
| Insertion          | 11491       |             |             |             |
| BinaryInsertion    | 377.0       | 808.57      | 241.33      | 242.5       |
| InsertionX         | 10444.33    |             |             |             |
| Merge              | 17.0        | 19.785      | 17.0        | 18.454      |
| MergeBU            | 19.16       | 14.35       | 24.0        | 21.4        |
| Selection          | 13209       | 23624.0     | 要花很长时间      | 要花很长时间      |
| Shell              | 72.16(波动较大) | 231.76      | 28.6        | 29.6        |
| QuickSortBase      | 报错          | 23.7692     | 13.33       | 13.1        |
| QuickSortIdentical | 12.666      | 17.21       | 14.25       | 16.0        |
| QuickSortNearOrder | 59.25       | 65.857      | 43.75       | 44.214      |
| QuickSortThreadWay | 26.0        | 24.928      | 20.4        | 19.153      |

### 各种排序的复杂度

| 类型                 | 时间复杂度（平均）            | 时间复杂度（最坏）            | 时间复杂度（最好）            | 空间复杂度                | 稳定性 |
|--------------------|----------------------|----------------------|----------------------|----------------------|-----|
| Insertion          | O(n2)                | O(n2)                | O(n)                 | O(1)                 | 稳定  |
| BinaryInsertion    |                      |                      |                      |                      |     |
| InsertionX         |                      |                      |                      |                      |     |
| Merge              | O(nlog<sub>2</sub>n) | O(nlog<sub>2</sub>n) | O(nlog<sub>2</sub>n) | O(n)                 | 稳定  |
| MergeBU            |                      |                      |                      |                      |     |
| Selection          | O(n<sup>2</sup>)     | O(n<sup>2</sup>)     | O(n<sup>2</sup>)     | O(1)                 | 稳定  |
| Shell              | O(n<sup>1.3</sup>)   | O(n<sup>2</sup>)     | O(n)                 | O(1)                 | 不稳定 |
| QuickSortBase      | O(nlog<sub>2</sub>n) | O(n<sup>2</sup>)     | O(nlog<sub>2</sub>n) | O(nlog<sub>2</sub>n) | 不稳定 |
| QuickSortIdentical |                      |                      |                      |                      |     |
| QuickSortNearOrder |                      |                      |                      |                      |     |
| QuickSortThreadWay |                      |                      |                      |                      |     |
| 堆排序                | O(nlog<sub>2</sub>n) | O(nlog<sub>2</sub>n) | O(nlog<sub>2</sub>n) | O(1)                 | 稳定  |
| 冒泡排序               | O(n<sup>2</sup>)     | O(n<sup>2</sup>)     | O(n<sup>2</sup>)     | O(1)                 | 稳定  |
| 计数排序               | O(n+k)               | O(n+k)               | O(n+k)               | O(n+k)               | 稳定  |
| 桶排序                | O(n+k)               | O(n<sup>2</sup>)     | O(n)                 | O(n+k)               | 稳定  |
| 技术排序               | O(n+k)               | O(n*k)               | O(n*k)               | O(n+k)               | 稳定  |

遍历方式：(深度优先搜索，广度优先搜索，前序遍历，中序遍历，后序遍历)

贪心算法
分治算法
回溯算法
动态规划
