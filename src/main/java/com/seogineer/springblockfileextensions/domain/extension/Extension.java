package com.seogineer.springblockfileextensions.domain.extension;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Extension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean useYn;

    private Boolean fixedYn;

    @Builder
    public Extension(String name, Boolean useYn, Boolean fixedYn){
        this.name = name;
        this.useYn = useYn;
        this.fixedYn = fixedYn;
    }

    public void update(String name, Boolean useYn){
        this.name = name;
        this.useYn = useYn;
    }

    public void changeFixedYn(){
        if(this.fixedYn){
            this.fixedYn = false;
        } else {
            this.fixedYn = true;
        }
    }
}
