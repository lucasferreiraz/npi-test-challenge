import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SociosService } from '../../services/socios.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';

@Component({
  selector: 'app-socio-form',
  templateUrl: './socio-form.component.html',
  styleUrls: ['./socio-form.component.scss']
})
export class SocioFormComponent {

  form = this.formBuilder.group({
    nome: [''],
    renda: [0],
    ativo: [false]
  })

  constructor(private formBuilder: FormBuilder,
    private service: SociosService,
    private snackBar: MatSnackBar,
    private location: Location) {
  }

  onSubmit() {
    const formValue = this.form.value;

    this.service.insert(formValue).subscribe(result => {
      this.onSucess()
      console.log(result)
    },
      error => this.onError())
  }

  onCancel() {
    this.location.back()
  }

  private onSucess() {
    this.snackBar.open("Sócio salvo com sucesso.", '', { duration: 4000 })
    this.onCancel()
  }

  private onError() {
    this.snackBar.open("Erro ao salvar Sócio.", '', { duration: 4000 })
    this.onCancel()
  }
}
