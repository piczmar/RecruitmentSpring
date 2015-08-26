@rem http://swientek.me/2014/02/03/benchmark-rest-endpoint-using-apache-bench/


ab -k -c 5 -n 100  -T application/json http://localhost:10000/Demo/products?onlyLast=true
ab -k -c 5 -n 100  -T application/json http://localhost:10000/Demo/products
ab -k -c 5 -n 100  -T application/json -p postfile.json http://localhost:10000/Demo/products