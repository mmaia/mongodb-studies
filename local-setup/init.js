conn = new Mongo('mongodb://root:rootpassword@localhost:27017');
db = conn.getDB('transactions');

db.transactions.createIndex({ "guid": "hashed"});
db.transactions.createIndex({ "guid": 1, "executedAt": 1});
db.transactions.createIndex({ "guid": 1, "executedAt": 1, "receivedMoney.currency": 1, "sentMoney.currency": 1, "type": 1});
db.transactions.createIndex({ "executedAt": -1});
