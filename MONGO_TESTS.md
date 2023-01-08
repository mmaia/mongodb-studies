# MongoDB tests

This page describes the tests to do some basic optimizations for MongoDB writes with Spring Data Mongo.

The document used for the test is the `TransactionDocument` and the indexes used are: 

- `id` (unique)
- `guid` (hashedIndex)
- `executedAt: -1`

Compound indexes: 

- `guid` and `executedAt`: 1
- `guid`, `executedAt`, `receivedMoney.currency`, `sentMoney.currency`, `type`: 1

## Performance tests

The tests were performed locally using this app and [mongostat](https://www.mongodb.com/docs/database-tools/mongostat/) with the
following command:

```bash
mongostat -u root --authenticationDatabase admin
```
`pwd: rootpassword`

1. First tests uses tag-v1 from repo and is a simple one at a time insert in the index

insert query update delete getmore command dirty used flushes vsize  res qrw arw net_in net_out conn                time
*0    *0   2394     *0       0     2|0  3.1% 4.4%       0 3.18G 836M 0|1 0|0  1.65m    395k    8 Jan  8 21:49:27.137
*0    *0   2017     *0       0     1|0  3.2% 4.4%       0 3.18G 836M 0|0 0|0  1.39m    343k    8 Jan  8 21:49:28.138
*0    *0   1850     *0       0     1|0  3.2% 4.4%       0 3.18G 836M 0|0 0|0  1.27m    319k    8 Jan  8 21:49:29.137
*0    *0   1771     *0       0     3|0  3.2% 4.4%       0 3.18G 836M 0|0 0|0  1.22m    309k    8 Jan  8 21:49:30.137
*0    *0   1987     *0       0     0|0  3.1% 4.4%       0 3.18G 836M 0|0 0|0  1.37m    338k    8 Jan  8 21:49:31.138
*0    *0   2104     *0       0     1|0  3.2% 4.4%       0 3.18G 837M 0|0 0|0  1.45m    355k    8 Jan  8 21:49:32.137
*0    *0   2089     *0       0     1|0  3.2% 4.4%       0 3.18G 838M 0|1 0|0  1.44m    353k    8 Jan  8 21:49:33.138
*0    *0   2198     *0       0     0|0  3.1% 4.4%       0 3.18G 840M 0|0 0|0  1.51m    368k    8 Jan  8 21:49:34.138
*0    *0   1844     *0       0     1|0  3.1% 4.4%       0 3.18G 841M 0|0 0|1  1.27m    318k    8 Jan  8 21:49:35.136
*0    *0   1951     *0       0     2|0  3.1% 4.5%       0 3.18G 843M 0|0 0|1  1.34m    333k    8 Jan  8 21:49:36.136

2. Second test uses tag-v2 from repo and uses batch inserts with 1000 documents per batch, overall performance was a bit better but not much:

insert query update delete getmore command dirty used flushes vsize  res qrw arw net_in net_out conn                time
*0    *0   2921     *0       0     1|0  0.9% 1.3%       0 2.67G 319M 0|0 0|0  2.01m    469k    8 Jan  8 21:55:22.189
*0    *0   2934     *0       0     1|0  0.9% 1.3%       0 2.68G 322M 0|0 0|0  2.02m    471k    8 Jan  8 21:55:23.189
*0    *0   2048     *0       0     1|0  1.0% 1.4%       0 2.68G 324M 0|0 0|1  1.41m    347k    8 Jan  8 21:55:24.189
*0    *0   2237     *0       0     0|0  1.0% 1.4%       0 2.68G 327M 0|0 0|0  1.54m    373k    8 Jan  8 21:55:25.190
*0    *0   2693     *0       0     2|0  1.0% 1.4%       0 2.68G 330M 0|0 0|0  1.85m    438k    8 Jan  8 21:55:26.191
*0    *0   2666     *0       0     1|0  1.0% 1.4%       0 2.69G 333M 0|0 0|0  1.83m    433k    8 Jan  8 21:55:27.190
*0    *0   2438     *0       0     1|0  1.0% 1.4%       0 2.69G 336M 0|0 0|0  1.68m    401k    8 Jan  8 21:55:28.189
*0    *0   2125     *0       0     2|0  1.0% 1.5%       0 2.69G 338M 0|1 0|0  1.46m    358k    8 Jan  8 21:55:29.189
*0    *0   2005     *0       0     0|0  1.1% 1.5%       0 2.69G 341M 0|1 0|0  1.38m    341k    8 Jan  8 21:55:30.190
*0    *0   2293     *0       0     2|0  1.1% 1.5%       0 2.70G 343M 0|0 0|0  1.58m    382k    8 Jan  8 21:55:31.189

