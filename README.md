这个是 logback 和 logstash 的普通结合，在 logback_logstash_config 文件夹里面有两个例子，分别对应的地 
- 把 logback.xml 放到 resource 文件夹中
- 把 logstash.conf 放到 /etc/logstash/conf.d/文件夹中就可以了
- 记得第三个是运用了 net.logstash.logback.encoder.LogstashEncoder，省去 grok 的烦恼

Logstash 工作原理：
- 有 input, filter, output 组成。
- input 是logstash 监听的地方，可以是文件夹，可以是 tcp:port, 可以是 kafka 等等，最后都会变成一个 message
- filter 是如何把 input 得到的 message 重新组合分配, 或者得到有用的信息，组合成 json 格式，然后每个 json key 就是类似列的，每个 json message 就是行。
- output 是把这个json message 发到什么地方去，可以是 elasticsearch, 可以是 graphite, 可以是 email

Filebeat 工作原理:
- 同样有 input, 有output, 但是没有filter
- filter 是需要的，但是类似 elasticsearch 作为最终目的地的话，要写在 elasticsearch 里面，例如一下例子就是如何写入一个 filter 到 es.
```
curl XPUT http://127.0.0.1:9200/_ingest/pipeline/pipeline-test

{
    "description": "describe pipeline",
    "processors": [
        {
            "grok": {
                "field": "message",
                "patterns": [
                    "--A_GROK_PRESENTATION--"
                ]
            }
        }
    ]
}
```
- filebeat 的好处在于远比logstash 快，而且内存使用少