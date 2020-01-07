2020年1月6日15:40:05
当前各种算法对于10万数据耗时

| 类型               | 异步执行8次(ms) | 异步执行16次(ms) | 同步执行16次(ms) | 同步执行16次(ms) |
| ------------------ | --------------- | ---------------- | ---------------- | ---------------- |
| Insertion          | 11491           |                  |                  |                  |
| BinaryInsertion    | 377.0           | 808.57           | 241.33           | 242.5            |
| InsertionX         | 10444.33        |                  |                  |                  |
| Merge              | 17.0            | 19.785           | 17.0             | 18.454           |
| MergeBU            | 19.16           | 14.35            | 24.0             | 21.4             |
| Selection          | 13209           | 23624.0          | 要花很长时间     | 要花很长时间     |
| Shell              | 72.16(波动较大) | 231.76           | 28.6             | 29.6             |
| QuickSortBase      | 报错            | 23.7692          | 13.33            | 13.1             |
| QuickSortIdentical | 12.666          | 17.21            | 14.25            | 16.0             |
| QuickSortNearOrder | 59.25           | 65.857           | 43.75            | 44.214           |
| QuickSortThreadWay | 26.0            | 24.928           | 20.4             | 19.153           |

### 各种排序的复杂度

| 类型               | 时间复杂度  |   空间复杂度   |
| ------------------ |   --------  |    ----------  |
| Insertion          |             |                |
| BinaryInsertion    |             |                |
| InsertionX         |             |                |
| Merge              |             |                |
| MergeBU            |             |                |
| Selection          |             |                |
| Shell              |             |                |
| QuickSortBase      |             |                |
| QuickSortIdentical |             |                |
| QuickSortNearOrder |             |                |
| QuickSortThreadWay |             |                |
