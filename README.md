**Run rabbitMQ docker command** <br />
`sudo docker run -d --name='activemq' -it --rm     -e 'ACTIVEMQ_CONFIG_MINMEMORY=512'     -e 'ACTIVEMQ_CONFIG_MAXMEMORY=2048'        -p 1883:1883 -p 5672:5672 -p 8161:8161 -p 61613:61613 -p 61614:61614 -p 61616:61616  webcenter/activemq:latest
`