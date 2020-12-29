1. About the JSON read and write: 
https://crunchify.com/in-java-how-to-convert-map-hashmap-to-jsonobject-4-different-ways/

感觉org.simple的做法像是jdbc。实际是用比较基础的原始办法生成JSON style的string。
而GSON和JACKSON则更像是后来的JPA，把code集成起来，用简单的method就可以直接处理一个pojo，
把它和对应的json直接转换。

2. About the inject
因为是stand along java application，所以要人工加上context container来处理inject。
如果是java EE app，则这些事情由server代为处理。道理是一样的。
