# JDK Oracle 유료화에 따른 알게된 내용

### OpenJDK 배포판의 Long-Term Support (LTS) 버전

| Java 버전  |
| ---------- |
| Java SE 8  |
| Java SE 11 |
| Java SE 17 |
| Java SE 21 |

### Oracle JDK의 유료화

**Oracle JDK는 Java SE 8까지 무료로 사용**할 수 있었습니다. 그러나 **Java SE 11부터**는 Oracle이 상업용 사용에 대해 구독 기반의 유료 모델을 도입했습니다. 다음은 주요 타임라인입니다:

- **2017년 9월**: Oracle은 Java SE의 새로운 릴리스 모델과 지원 정책을 발표했습니다.
- **2018년 1월**: Oracle JDK 11의 초기 액세스 빌드가 출시되었습니다.
- **2018년 9월**: Java SE 11이 정식으로 출시되었습니다. 이 버전부터 Oracle JDK는 상업용 사용에 대해 유료 구독이 필요하게 되었습니다.

Oracle은 Java SE 8까지는 보안 업데이트와 버그 수정 등을 무료로 제공했지만, Java SE 11 이후부터는 상업용으로 사용할 경우 Oracle의 Java SE Subscription을 통해 구독 비용을 지불해야 합니다. Java SE 11부터는 LTS(Long-Term Support) 릴리스마다 3년마다 새로운 LTS 릴리스가 나옵니다.

### TCK란 무엇이며, 누가 제공하는가?

**TCK (Technology Compatibility Kit)**는 특정 Java Platform Edition의 호환성을 검증하는 도구 모음입니다. TCK는 다음과 같은 역할을 합니다:

- **테스트 케이스**: Java 플랫폼의 API와 기능이 규격에 맞게 구현되었는지를 검증하는 테스트 케이스 모음입니다.
- **호환성 확인**: 특정 Java 구현체가 Java 표준과 호환되는지를 확인합니다.

**TCK는 Oracle이 제공**하며, Java Community Process (JCP)의 일환으로 유지 관리됩니다. Java 플랫폼의 각 버전에 대해 TCK는 해당 버전의 사양을 준수하는지 여부를 확인하는 테스트를 포함합니다.



## TCK를 인증받은 JDK 목록

TCK(Technology Compatibility Kit) 인증을 받은 JDK는 Java 플랫폼의 표준과 호환성을 보장합니다. 아래는 무료로 사용 가능하며 TCK 인증을 받은 JDK 배포판 목록입니다

### 1. **Eclipse Adoptium (Temurin)**

Eclipse Adoptium 프로젝트는 AdoptOpenJDK의 후속 프로젝트로, Temurin JDK 배포판을 제공합니다. Temurin은 TCK 인증을 받은 JDK 배포판 중 하나입니다.

- **공식 웹사이트**: [Eclipse Adoptium](https://adoptium.net/)

| Java  Version | First Availability | Latest Release | Next Release Due | End of Availability [1] |
| ------------- | ------------------ | -------------- | ---------------- | ----------------------- |
| Java 21 (LTS) | Sep-23             | 16-Apr-24      | 16-Jul-24        | At least Dec 2029       |
| Java 17 (LTS) | Sep-21             | 18-Apr-24      | 16-Jul-24        | At least Oct 2027       |
| Java 11 (LTS) | Sep-18             | 18-Apr-24      | 16-Jul-24        | At least Oct 2027       |
| Java 8 (LTS)  | Mar-14             | 18-Apr-24      | 16-Jul-24        | At least Nov 2026       |

### 2. **Amazon Corretto**

Amazon Corretto는 Amazon에서 제공하는 무료, 멀티플랫폼, 생산 준비가 된 OpenJDK 배포판입니다. Corretto는 TCK 인증을 받아 Java SE 표준과 호환됩니다.

- **공식 웹사이트**: [Amazon Corretto](https://aws.amazon.com/corretto/)

| **Coretto 릴리스** | **릴리스 유형** | **GA 날짜**     | **마지막으로 계획된 업데이트** | **지원 종료** |
| ------------------ | --------------- | --------------- | ------------------------------ | ------------- |
| 22                 | FR              | 2024년 3월 19일 | 2024년 7월                     | 2024년 10월   |
| 21                 | LTS             | 2023년 9월 21일 | 2030년 7월                     | 2030년 10월   |
| 17                 | LTS             | 2021년 9월 16일 | 2029년 7월                     | 2029년 10월   |
| 11                 | LTS             | 2019년 3월 15일 | 2027년 7월                     | 2027년 10월   |
| 8                  | LTS             | 2019년 1월 31일 | 2026년 4월                     | 2026년 7월    |

### 3. **Azul Zulu**

Azul Zulu는 Azul Systems에서 제공하는 상업적 지원이 가능한 OpenJDK 빌드입니다. Zulu는 다양한 플랫폼에서 사용할 수 있으며, TCK 인증을 받은 무료 버전을 제공합니다.

- **공식 웹사이트**: Azul Zulu

### 4. **Red Hat OpenJDK**

Red Hat은 OpenJDK 배포판을 제공하며, TCK 인증을 받았습니다. 이 배포판은 Red Hat Enterprise Linux와 함께 사용되며, 무료로 사용할 수 있습니다.

- **공식 웹사이트**: Red Hat Developer