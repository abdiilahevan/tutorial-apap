# Tutorial APAP
## Authors
* **Abdillah Evan Nurdhiawan** - *1906398805* - *APAP-A*

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