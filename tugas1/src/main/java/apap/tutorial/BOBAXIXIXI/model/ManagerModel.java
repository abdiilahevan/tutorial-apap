package apap.tutorial.BOBAXIXIXI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter 
@Getter
@Entity
@Table(name = "manager")

public class ManagerModel implements Serializable{
    
    @Id
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger noManager;

    @NotNull
    @Size(max = 255)
    @Column(name = "full_name", nullable = false)
    private String namaManager;

    @NotNull
    @Column(name = "gender", nullable = false)
    private int jenisKelamin;

    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    private LocalDateTime tanggalLahir;
}
