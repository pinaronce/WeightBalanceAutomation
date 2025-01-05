# Weight Balance Automation Project

## Proje Hakkında
Bu proje, uçak ağırlık ve denge hesaplamalarının otomatize edilmiş testlerini içeren bir test otomasyon projesidir. Selenium WebDriver ve Cucumber BDD framework'ü kullanılarak Java programlama dili ile geliştirilmiştir.

## Teknolojiler ve Özellikler
- Java 22
- Selenium WebDriver 4.27.0
- Cucumber 7.15.0
- JUnit 5.10.2
- Log4j 2.23.0
- Maven
- Cucumber HTML Reports

## Proje Yapısı
```
weight-balance-automation/
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   └── com/thy/
│   │   │       ├── driver/
│   │   │       │   ├── BrowserConfig.java
│   │   │       │   └── DriverManager.java
│   │   │       ├── elements/
│   │   │       │   ├── HomePageElements.java
│   │   │       │   └── LocatorRepository.java
│   │   │       ├── methods/
│   │   │       │   └── BaseMethods.java
│   │   │       ├── runners/
│   │   │       └── steps/
│   │   └── resources/
│   │       ├── features/
│   │       └── cucumber.properties
├── drivers/
├── logs/
├── reports/
├── pom.xml
└── README.md
```

## Kurulum
1. Gereksinimleri yükleyin:
   - Java 22 JDK
   - Maven

2. Projeyi klonlayın:
```bash
git clone https://github.com/yourusername/weight-balance-automation.git
cd weight-balance-automation
```

3. Maven bağımlılıklarını yükleyin:
```bash
mvn clean install
```

## Testleri Çalıştırma
Tüm testleri çalıştırmak için:
```bash
mvn clean test
```

Belirli bir tag ile testleri çalıştırmak için:
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

## Raporlama
Test çalıştırması tamamlandıktan sonra, raporlar aşağıdaki konumda oluşturulacaktır:
- HTML Report: `target/cucumber-reports/report.html`
- JSON Report: `target/cucumber-reports/cucumber.json`

## Loglama
- Log dosyaları `logs/` dizininde oluşturulur
- Log mesajları hem İngilizce hem Türkçe olarak kaydedilir
- Log seviyeleri: INFO, ERROR, DEBUG

## Test Framework Özellikleri
- Page Object Model (POM) tasarım deseni
- Cucumber BDD yaklaşımı
- Çift dilli (TR/EN) log desteği
- Paralel test çalıştırma desteği
- Detaylı HTML raporlama
- Screenshot özelliği
- Cross-browser test desteği

## Katkıda Bulunma
1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Lisans
Bu proje MIT lisansı altında lisanslanmıştır - Detaylar için [LICENSE](LICENSE) dosyasına bakınız.