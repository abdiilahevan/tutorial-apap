# Tutorial APAP
## Authors
* **Abdillah Evan Nurdhiawan** - *1906398805* - *APAP-A*

## Tutorial 6
### What I learn today
Labnya sulit T_T, tapi enjoy. sama seperti ppw, di apap juga bisa role based authorization

1. Jelaskan secara singkat perbedaan Otentikasi dan Otorisasi! Di bagian mana (dalam kode
yang telah anda buat) konsep tersebut diimplementasi?
- Otentikasi adalah proses pengenalan seorang user di sebuah sistem, hal ini ditentukan dari jawaban si user apakah sesuai dengan kredensial user yang tersimpan di database sistem. apabila terjadi kecocokan kombinasi lock & key (dalam artian ini berarti username dan password) yang dimasukkan oleh user saat ditanya dengan yang tersimpan di databse, maka user dapat masuk.
- Otorisasi berarti penentuan hak akses sumber daya sistem, masing - masing user dapat memiliki tingkat otorisasi yang sama maupun berbeda, tergantung degan kredensial yang dimiliki oleh user saat otentikasi
- konsep otentikasi diaplikasikan saat sistem meminta kombinasi username dan password
- konsep otorisasi  diaplikasikan saat user berhasil melewati tahap otentikasi dan dicek role apa yang dimiliki di package security

2. Apa itu BCryptPasswordEncoder? Jelaskan secara singkat cara kerja dan tujuannya
- BCrypt adalah fungsi hashing kata sandi yang berguna untuk mengenkripsikan saut arah ketika password sudah dienkripsi dan tidak dapat didekripsi kembali. 
- BCryptPasswordEncoder adalah class dari spring boot yang berguna untuk menencode password menjadi kode unik agar sulit untuk ditiru dan meningkatkan keamanan password

3.  Apakah penyimpanan password sebaiknya menggunakan encryption atau hashing? Mengapa
demikian?
- Lebih baik menggunakan enkripsi karena merskipun dengan hashing lebih aman karena one way function yang mana akan sulit untuk mendapatkan password aslinya once sudah diset. dengan enkripsi maka akan lebih mudah mendapatkan password aslinya karena dengan adanya cara ini, user tidak perlu berkali kalai membuat akun ketika ia lupa password.

4. Jelaskan secara singkat apa itu UUID beserta penggunaannya!
- Universal Unique Identifier (UUID) merupakan karakter random yang berguna sebagai id sebuah akun. UUID cocok dijadikan PK karena unique

5. Apa kegunaan class UserDetailsServiceImpl.java? Mengapa harus ada class tersebut
padahal kita sudah memiliki class UserRoleServiceImpl.java?
- UserDetailsServicelmpl.java berguna dalam proses otentikasi, yaitu memvalidasi username dan password yang diinput user dengan yang ada di database.
- UserServicelmpl mendefinisikan hal yang dapat dilakukan oleh user yang sedang login dan apa yang bisa dilakukan dengan user di database. 
- Sedangkan RoleServiceImpl mendefinisikan hal yang dapat dilakukan oleh role tersebut dan apa
yang bisa dilakukan dengan role di database.

## Tutorial 5
### What I learn today
Labnya sulit T_T, tapi enjoy walau gakelar nomor 2. Penggunaan postman sangat berguna dalam proses pengembangan aplikasi karena menyimpan banyak shortcut pentng dalam melakukan test yang berguna dalam pengembangan
1.  Apa itu Postman? Apa kegunaannya?
- Postman adalah pekerjaan mengantar surat (bercanda)
- Postman adalah aplikasi yang digunakan dalam melakukan API testing
- Merupakan HTTP client yang menerima HTTP reqest dan dapat memberikan berbagai macam respon dan dapat mengeset respons yang diharapkan. in summary, sangat berguna dalam pengembangan aplikasi.

2. Jelaskan fungsi dari anotasi @JsonIgnoreProperties dan @JsonProperty
- JsonIgnoreProperties : Menandai property yang terhubung dengan kelas yang dikenai anotasi. akan berguna untuk menghentikan loop
- JsonProperty : menandai property khusus seperti setter getter

3.  Apa kegunaan atribut WebClient?
- suatu komponen yang digunakan untuk melakukan HTTP Calls terhadap service lainnya.

4. Apa itu ResponseEntity dan BindingResult? Apa kegunaannya?
- Response entity : keseluruhan HTTP response, termasuk status code, header, dan body http
- kegunaan : digunakan untuk mengonfigurais HTTP response
- Binding Result : Objek dalam spring yang mengikat hasil validasi dan dapat mengatur error yang mungkin dapat muncul
- Kegunaan : memprediksi error yang mungkin akan terjadi

## Tutorial 4
### What I learn in these 2 days
HTML sangat menyenangkan sekaligus sulit

1. Jelaskan perbedaan th:include dan th:replace!
- Dari arti secara bahasanya sendiri include adalah memasukkan, sementara replace mengganti
- th: include akan memasukkan konten yang ada di file fragment ke tag tujuan
- th: replace akan mengganti konten yang ada di tag tujuan dengan konten yang ada di fragmen

2. Jelaskan apa fungsi dari th:object!
- th:object digunakan untuk mengspesifikasi objek yang disubmit dari form ke tempat penyimpanan sesuai dengan nama objectnya, hal ini dapat ditemui di file bertajuk form di html pada lab 4 ini.

3. Jelaskan perbedaan dari * dan $ pada saat penggunaan th:object! Kapan harus dipakai?
- tanda * digunakan untuk mengambil atribut yang sudah didefinisikan dari suatu objek yang ingin diakses
- tanda $ digunakan untuk atribut yang belum didefinisikan dari suatu objek, sehingga harus mengakses objeknya terlebih dahulu


## Tutorial 3
### What i learn today
banyak yang baru saya pelajari terutama mengenai masalah dependency karena banyak masalah

1. Tolong jelaskan secara singkat apa kegunaan dari anotasi-anotasi yang ada pada model
(@AllArgsConstructor, @NoArgsConstructor, @Setter, @Getter, @Entity, @Table)

- @AllArgsConstructor = Membuat constructor dengan parameter untuk setiap field di kelas tersebut secara otomatis untuk menhindari null.

- @NoArgsConstructor = Membuat constructor tanpa parameter. Kalau karena satu dan lain hal tidak bisa digenerate, maka akan ada compiler error **Kecuali**  ada @NoArgsConstructor(force = true) maka akan menjadikan fieldnya 0/null/false.

- @Setter = Membuat setter secara otomatis
- @Getter = membuat getter secara otomatis

- @Entity = Untuk mencatat apakah ini sebuat tabel atau sebuah entity
- @Table = Untuk memberi nama tabel, maka dari itu sintaksnya adalah @Table(name = "XXX")

Cr : https://projectlombok.org/features/constructor , https://projectlombok.org/features/GetterSetter , https://stackoverflow.com/questions/7259273/whats-the-difference-between-the-name-argument-in-entity-and-table-when-using#:~:text=2.%20%40Entity%20is%20useful%20with%20model%20classes%20to,that%20%40Entity%20is%20your%20table%20name%20by%20default.

2. Pada class CabangDB, terdapat method findByNoCabang, apakah kegunaan dari method tersebut?

- Mencari sebuah data dari tabel Cabang berdasarkan nomor dari cabang tersebut, parameter yang dimasukkan dalam kurung adalah nomor tabel yang dicari. Jika diumpamakan sebagai SQL mungkin akan menjadi Select "[Nomor yang dicari]" from NoCabang .

3. Jelaskan perbedaan kegunaan dari anotasi @JoinTable dan @JoinColumn
- @JoinTable menyimpan id dari kedua entity ke tabel terpisah
    - Digunakan untuk menangani relation diantara 2 entity di tabel terpisah
    - Digunakan untuk menormalisasi database + mengurangi redundancy

- @JoinColumn menyimpan id dari entity lain digabungkan ke satu tabel
    - Digunakan ketika terdapat relasi foreign key diantara dua entity
    - Digunakan jika lebih membutuhkan performance karena tidak butuh tabel extra

Cr : https://javakeypoint.wordpress.com/2020/04/21/difference-between-joincolumn-and-jointable-in-hibernate/

4. Pada class PegawaiModel, digunakan anotasi @JoinColumn pada atribut cabang, apa kegunaan dari name, referencedColumnName, dan nullable dalam anotasi tersebut? dan apa perbedaan nullable dan penggunaan anotasi @NotNull

- Jika kita lihat full code nya : @JoinColumn(name = "noCabang", referencedColumnName = "noCabang", nullable = false)

- JoinColumn berguna untuk menyatakan penggabungan antara tabel PegawaiModel dengan CabangModel (di atas dideclare import CabangModel) **Terkhusus untuk Kolom yang diinginkan**. 

- name, singkatnya adalah kolom Foreign Key, sementara referencedColumnName berisi Primary Key, nullable adalah sebuah boolean yang menyatakan apakah kolom foreign key boleh null (defaultnya adalah true), pada contoh berarti data tidak boleh null.

- nullable dan @NotNull tentunya berbeda dimana nullable berkaitan dengan Java sementara @NotNull berkaitan dengan database (JPA). Sebagai anotasi Java, nullable akan dicek terlebih dulu dibandingkan @NotNull. Dengann menggunakan nullable, maka database akan langsung membuat kolom not null, hal ini dapat compatible jika digunakan aplikasi lain ketika menggunakan database, namun lebih amannya gunakan keduanya.

5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER
- FetchType.LAZY = data akan diditangkap **Hanya** data akan yang digunakan saja, jika tidak digunakan tidak akan ditangkap, hal ini berlaku untuk seluruh data
- CascadeType.ALL = mengatur cascade untuk setiap perubahan yang terjadi pada data (update dan delete), kerjanya seperti cascade pada sql
- FetchType.EAGER = Semua data yang berelasi akan ditangkap sesaat setelah data digunakan 

Cr : https://www.concretepage.com/hibernate/fetch_hibernate_annotation


## Tutorial 2
### What I have learned today
Terdapat berbagai macam cara untuk meredirect halaman melalui search bar, dalam pengaksesan melalui backend di html & contoller juga berbagai macam cara, Saya mengetahui hal ini karena tadi ada error ketika mengikuti template

### Contorller
1. Cobalah untuk menambahkan sebuah Kebun dengan mengakses link
berikut setelah menjalankan program:
http://localhost:8080/kebun-safari/add?id=1&nama=Papa%20APAP&alamat=Maung%
20Fasilkom&noTelepon=081xxx 
Apa yang terjadi? Jelaskan mengapa hal tersebut
dapat terjadi

- Pada kali pertama menemui soal ini, Saya langsung menuju link yang ditanyakan dan berakibat pada error whitelabel yang tentunya dikarenakan tidak adanya file html yang dituju, karena belum dibuat.
- Setelah dibuat, tentunya sudah meng-generate halaman yang diminta

2. Menurut kamu anotasi @Autowired pada class Controller tersebut
merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja
@Autowired tersebut dalam konteks service dan controller yang telah kamu buat

- Autowired tersebut merupakan implementasi dari konsep Dependency Injection yang berguna untuk mempersingkat kode dengan mengimplementasi constructor yang dibuat di interface
- Pada tutorial ini, apap.tutorial.kebunsafari.service berperan sebagai interface yang diinject constructornya ke controller. Hal ini membuat kita tidak perlu lagi menulis ulang constructor di controller

3. Cobalah untuk menambahkan sebuah Kebun dengan mengakses link
berikut:
http://localhost:8080/kebun-safari/add?id=1&nama=Papa%20APAP&alamat=Maung%
20Fasilkom Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.

- Tentunya akan menampilkan error karena ada salah satu parameter yang tidak terisi, yaitu nomor telepon
- Padahal pada controller, nomor telepon merupakan item wajib karena required = true

4. Jika Papa APAP ingin melihat Kebun Safari dengan nama Papa APAP,
link apa yang harus diakses?

- Menggunakan ini bisa : http://localhost:8080/kebun-safari?id=1&nama=Papa%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx
- Menggunakan ini juga bisa : http://localhost:8080/kebun-safari?id=1

5. Tambahkan 1 contoh Kebun Safari lainnya sesukamu. Lalu cobalah
untuk mengakses http://localhost:8080/ , apa yang akan ditampilkan? Sertakan juga
bukti screenshotmu.

- ![foto tutorial 2] (Image/Tutor2.png)

### What did i dont understand
sebenarnya sampai sekarang Saya tidak tahu kenapa kode Saya tidak bisa berjalan jika mengikuti template, masih menjadi misteri ilahi / vscode magic :D

## Tutorial 1
### What I have learned today
Syntax yang digunakan untuk mata kuliah APAP ini masih sangat asing bagi Saya, Harus belajar lebih giat lagi
### Github
1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?
    - Issue tracker merupakan semacam log yang diisi secara manual oleh seorang developer / lead / IT project Manager yang berguna untuk melaporkan progress maupun hambatan yang terjadi
    - Issue yang dituliskan dalam issue tracker dapat dilihat oleh penguna lain yang memiliki akses ke project repository sebagai kolaborator
    - Kolaborator lain dapat menyelesaikan masalah dengan melakukan pull & push request ke reposity tersebut jika mengetahui penyelesaian dari issue tersebut
    - Dengan rajin melakukan update dalam issue tracker, progress project dapat lebih mudah terdokumentasikan dan dapat diketahui issue apa saja yang ditemui dalam pengembangan project

2. Apa perbedaan dari git merge dan git merge --squash?
    - git merge adalah proses penggabungan 2 branch dalam sebuah repo di sebuah project, isi repo dari penggunaan merge ini merupakan gabungan (berparent) dari 2 branch yang dimerge tadi
    - git merge --squash mirip dengan git merge biasa, hanya saja posisi parent yang diambil hanya satu, yaitu cabang utamanya, sementara cabang satunya lagi tidak disertakan namun isi datanya sudah tergabungkan di akhir repo

    Sumber : https://devblogs.microsoft.com/devops/squash-a-whole-new-way-to-merge-pull-requests/

3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan
suatu aplikasi?
    - Dengan menggunakan git, user dapat lebih mudah mengetrack pekerjaan ; Dapat merevert ke versi sebelumnya dengan pengaturan yang mudah ; dapat memilih fokus pekerjaan dengan fitur branch ; fitur pull & push yang dapat diatur kapan masuk dan keluarnya dan diawasi oleh user lainnya yang sebagai kolaborator

### Spring
4. Apa itu library & dependency?
    - library adalah koleksi kode yang dapat langsung digunakan oleh pengguna lain secara cepat. Biasanya sudah ditulis lebih dulu oleh pengembang lain agar pengguna lainnya dapat langsung menggunakan fungsi yang diinginkan
    - dependency adalah suatu program yang perlu ada agar program lainnya dapat berjalan. sebut saja program A hanya dapat berjalan jika ada program B, maka program B adalah dependency dari program A, tidak berlaku sebaliknya

5. Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven?
    - Maven adalah alat / tools yang membantu dalam pengembangan project / product. Maven mendukung penggunaan berbagai bahasa seperti Java, Ruby, C++, dan sebagainya.
    - Maven digunakan untuk mengautomasi penyetingan project, terutama pada tahap awal project untuk pengumpulan dependency dan secara automatis akan terunduh dengan maven.
    - Selain maven, masih banyak tools lain yang dapat digunakan, terutama untuk Java developer, seperti Red Hat Ansible Atomation Platform, Azure, Jenkins, GitHub, dan Circle CI, dan masih banyak lagi.

6. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring
framework?
    - Fungsi utama spring adalah untuk membantu developer mengembangkan web, Spring mengurus infrastruktur dari web, sementara pengembang hanya perlu berfokus di aplikasinya
    - Bisa juga digunakan sebagai database dan juga digunakan untuk bot (misal di discord)

7. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya
menggunakan @RequestParam atau @PathVariable?
    - Baik @RequestParam dan @PathVariable digunakan untuk mengambil value dari parameter yang diminta
    - @RequestParam digunakan untuk mengakses value dari query parameter
    - @PathVariable mengakses value dari URL template yang terhubung

### What I did not understand
 Sebenarnya Saya masih belum cukup memahami materi APAP minggu pertama ini, mungkin karena masih merupakan teknologi yang baru Saya pelajari. Untuk pertanyaan nomor 6 dan 7 Saya masih kurang mengerti pendalamannya dari bacaan yang Saya baca sehingga yang Saya jawab hanya berdasarkan pemahaman Saya saja.