# Weight & Balance Automation Project

## Proje Hakkında
Bu proje, THY Weight & Balance sisteminin test otomasyonunu içermektedir. Cucumber BDD framework'ü ve Selenium Grid altyapısı kullanılarak geliştirilmiştir.

## Teknolojiler ve Özellikler
- Java 23
- Selenium WebDriver 4.x
- Selenium Grid
- Cucumber BDD
- TestNG
- Maven
- Remote WebDriver desteği
- Parallel test execution
- Cross-browser testing

## Proje Yapısı
```
src/
├── test/
│   ├── java/com/thy/
│   │   ├── base/
│   │   │   ├── BrowserConfig.java        # Browser configurations
│   │   │   ├── ConfigurationManager.java # Configuration management
│   │   │   ├── DriverManager.java        # WebDriver management
│   │   │   └── DriverPathManager.java    # Driver path management
│   │   ├── runners/
│   │   │   └── TestRunner.java          # Test runner
│   │   └── steps/
│   │       └── BaseSteps.java           # Step definitions & Hooks
│   └── resources/
│       └── log4j2.xml                   # Logging configuration
```

## Özellikler

### Selenium Grid Entegrasyonu
- Remote WebDriver desteği
- Parallel test execution
- Cross-browser testing
- Farklı platformlarda test çalıştırma

### Çoklu Tarayıcı Desteği
- Chrome
- Firefox 
- Edge
- Safari
- Headless execution

### BDD Implementation
- Cucumber entegrasyonu
- Business-readable specifications
- Reusable step definitions
- Çoklu dil desteği (Türkçe/İngilizce)

### Test Yönetimi
- Page Object Model
- Fluent wait stratejileri
- Screenshot capture
- HTML raporlama

## Kurulum

### Gereksinimler
- Java 11 JDK
- Maven 3.8+
- Selenium Grid (opsiyonel)
- WebDriver binary'leri

### Projeyi Çalıştırma

#### Tüm testleri çalıştırma:
```bash
mvn test
```

#### Grid üzerinde çalıştırma:
```bash
mvn test -DuseGrid=true -DgridUrl=http://localhost:4444/wd/hub
```

#### Belirli bir tarayıcıda çalıştırma:
```bash
mvn test -Dbrowser=chrome
```

## Raporlama
- Cucumber HTML raporları
- TestNG raporları
- Screenshot'lar
- Execution timeline
- Hata logları

## Katkıda Bulunma
1. Repository'yi fork edin
2. Feature branch oluşturun (`git checkout -b feature/YeniOzellik`)
3. Değişikliklerinizi commit edin (`git commit -m 'Yeni özellik eklendi'`)
4. Branch'inizi push edin (`git push origin feature/YeniOzellik`)
5. Pull Request oluşturun

## Driver Kurulumu

1. Chrome tarayıcınızın versiyonunu öğrenin:
   - Chrome'u açın
   - `chrome://version` adresine gidin
   - Versiyon numarasını not alın (örn: 120.0.6099.130)

2. ChromeDriver indirin:
   - https://chromedriver.chromium.org/downloads adresine gidin
   - Chrome versiyonunuza uygun driver'ı indirin
   - Zip/rar dosyasını açın

3. Driver dosyasını kopyalayın:
   - Projenin root klasöründe `drivers` klasörü oluşturun
   - Windows için: `chromedriver.exe`'yi bu klasöre kopyalayın
   - Linux/Mac için: `chromedriver`'ı bu klasöre kopyalayın

4. Linux/Mac için ek adım:
   ```bash
   chmod +x drivers/chromedriver
   ```