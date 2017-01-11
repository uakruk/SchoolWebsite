# SchoolWebsite
Kordelivka school website<br>
<h4>building:</h4>
<code>sudo docker-compose up --abort-on-container-exit web-build</code>
<h4>running(port 8081):</h4>
<code>sudo docker-compose up web-run</code>
<h4>Using ELK stack:</h4>
<ul>
  <li> Elasticsearch: <code>sudo docker-compose up -d elasticsearch</code></li>
  <li> Logstash(with elasticsearch): <code>sudo docker-compose up -d logstash</code></li>
  <li> Kibana(port 5602): <code>sudo docker-compose up -d logstash</code></li>
</ul>
<p>After running ELK stack and application you can see visualized logs on <b>localhost:5602</b> (kibana)</p>
<h4>IMPORTANT</h4>
<p>Before starting using elasticsearch, execute <code>sudo sysctl -w vm.max_map_count=262144</code></p>
<h4>Demonstrating kibana:</h4>
![alt tag](https://raw.githubusercontent.com/uakruk/SchoolWebsite/test/misc/logs.png)
<p>Here you can see that logs had been pulled by kibana from ES.</p>
![alt tag](https://raw.githubusercontent.com/uakruk/SchoolWebsite/test/misc/events.png)
<p>And there are some events(visiting login and register pages) on histogram.</p>

