import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SociosService } from '../../services/socios.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { Socio } from '../../model/socio';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-socio-form',
  templateUrl: './socio-form.component.html',
  styleUrls: ['./socio-form.component.scss']
})
export class SocioFormComponent implements OnInit {

  form = this.formBuilder.group({
    id: new FormControl<string | number>(null),
    nome: new FormControl<string>(null, [
      Validators.required,
      Validators.minLength(5),
      Validators.maxLength(50),
    ]),
    renda: new FormControl<string | number>(null, [
      Validators.required,
      Validators.min(0)
    ]),
    ativo: new FormControl<string | boolean>(null, [
      Validators.required
    ])
  })

  constructor(private formBuilder: FormBuilder,
    private service: SociosService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    const socio: Socio = this.route.snapshot.data['socio']
    this.form.setValue({
      id: socio.id,
      nome: socio.nome,
      renda: socio.renda,
      ativo: socio.ativo
    })
  }

  onSubmit() {
    const formValue = this.form.value;

    this.service.save(formValue).subscribe(result => {
      this.onSucess()
      console.log(result)
    },
      error => this.onError())
  }

  onCancel() {
    this.location.back()
  }

  private onSucess() {
    this.snackBar.open("Operação concluida com sucesso.", '', { duration: 4000 })
    this.onCancel()
  }

  private onError() {
    this.snackBar.open("Erro ao salvar Sócio.", '', { duration: 4000 })
    this.onCancel()
  }
}
