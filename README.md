# grpc-demo

Minimal Spring Boot 4.1 project showing native gRPC support: a server exposing
a `HelloWorld` service, and a client stub that calls it on startup.

## Requirements

- JDK 21+
- Maven 3.9+

## Project layout

```
src/main/proto/hello.proto           - service + message definitions
src/main/java/.../MyHelloWorldService.java   - server implementation (@GrpcService)
src/main/java/.../HelloClientRunner.java     - client stub call on startup
src/main/java/.../GrpcDemoApplication.java   - main class, @ImportGrpcClients
src/main/resources/application.yml           - server port + client channel config
src/test/java/.../GrpcDemoApplicationTests.java - in-process test transport example
```

## Build

```bash
mvn clean package
```

This runs the `protobuf-maven-plugin` first, generating Java classes from
`hello.proto` into `target/generated-sources/protobuf` before compiling.

## Run

```bash
mvn spring-boot:run
```

On startup you should see:

```
>>> gRPC client received: Hello 'Spring'
```

The server listens on port `9090` (Netty). You can also call it directly with
[grpcurl](https://github.com/fullstorydev/grpcurl) since the reflection
service is on the classpath:

```bash
grpcurl -d '{"name":"World"}' -plaintext localhost:9090 HelloWorld.SayHello
```

## Test

```bash
mvn test
```

The test uses `@AutoConfigureTestGrpcTransport`, so it exercises the full
client -> server call without binding to a real network port.

## Notes

- No `net.devh` starter needed — `spring-boot-starter-grpc-server` and
  `spring-boot-starter-grpc-client` are native to Spring Boot 4.1.
- If you hit Netty version conflicts with other libraries, swap
  `io.grpc:grpc-netty` for `io.grpc:grpc-netty-shaded` (see README section
  in Spring Boot's own gRPC docs for the exact exclusion).
