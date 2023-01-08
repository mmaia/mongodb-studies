# MongoDB tests

This page describes the tests to do some basic optimizations for MongoDB writes with Spring Data Mongo.

The document used for the test is the `TransactionDocument` and the indexes used are: 

- `id` (unique)
- `guid` (hashedIndex)
- `executedAt: -1`

Compound indexes: 

- `guid` and `executedAt`: 1
- `guid`, `executedAt`, `receivedMoney.currency`, `sentMoney.currency`, `type`: 1

1. First tests is a simple one at a time insert in the index

insert query update delete getmore command dirty used flushes vsize   res qrw arw net_in net_out conn                time
*0    *0   2617     *0       0     4|0  2.8% 8.5%       0 3.82G 1.41G 0|0 0|0  1.72m    428k   29 Jan  8 11:35:22.107
*0    *0   2911     *0       0     1|0  2.8% 8.5%       0 3.82G 1.41G 0|0 0|0  1.92m    468k   29 Jan  8 11:35:23.106
*0    *0   2746     *0       0     0|0  2.9% 8.5%       0 3.82G 1.41G 0|0 0|0  1.81m    445k   29 Jan  8 11:35:24.107
*0    *0   2618     *0       0     2|0  2.9% 8.5%       0 3.82G 1.41G 0|0 0|0  1.72m    427k   29 Jan  8 11:35:25.110
*0    *0   2008     *0       0     0|0  2.9% 8.5%       0 3.82G 1.41G 0|0 0|0  1.32m    341k   29 Jan  8 11:35:26.111
*0    *0   1675     *0       0     7|0  2.9% 8.5%       0 3.82G 1.41G 0|0 0|0  1.10m    297k   29 Jan  8 11:35:27.110
*0    *0   1823     *0       0     1|0  2.9% 8.5%       0 3.82G 1.41G 0|0 0|0  1.20m    315k   29 Jan  8 11:35:28.110
*0    *0   1806     *0       0     0|0  2.9% 8.5%       0 3.82G 1.41G 0|1 0|0  1.19m    313k   29 Jan  8 11:35:29.110
*0    *0   2304     *0       0     1|0  2.9% 8.5%       0 3.82G 1.41G 0|0 0|0  1.52m    383k   29 Jan  8 11:35:30.110
*0    *0   3000     *0       0     2|0  2.9% 8.5%       0 3.82G 1.41G 0|0 0|1  1.97m    481k   29 Jan  8 11:35:31.107
