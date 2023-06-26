import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, UntypedFormArray, Validators } from '@angular/forms';
import { SociosService } from '../../services/socios.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { Socio } from '../../model/socio';
import { ActivatedRoute } from '@angular/router';
import { Dependente } from '../../model/dependente';
import { DependentesService } from '../../services/dependentes.service';

@Component({
  selector: 'app-socio-form',
  templateUrl: './socio-form.component.html',
  styleUrls: ['./socio-form.component.scss']
})
export class SocioFormComponent implements OnInit {

  form!: FormGroup

  constructor(private formBuilder: FormBuilder,
    private socioService: SociosService,
    private dependenteService: DependentesService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    const socio: Socio = this.route.snapshot.data['socio']

    this.form = this.formBuilder.group({
      id: [socio.id],
      nome: [socio.nome, [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(50),
      ]],
      renda: [socio.renda, [
        Validators.required,
        Validators.min(0)
      ]],
      ativo: [socio.ativo ? 'true' : 'false', [
        Validators.required
      ]],
      dependentes: this.formBuilder.array(this.retrieveDependente(socio))
    })
  }

  addNewDependente() {
    const dependentes = this.form.get('dependentes') as UntypedFormArray
    dependentes.push(this.createDependente())
  }

  removeDependente(index: number) {
    const dependentes = this.form.get('dependentes') as UntypedFormArray;
    const dependenteId = dependentes.at(index).value.id;

    if (dependenteId) {
      this.dependenteService.delete(dependenteId).subscribe(() => {
        dependentes.removeAt(index);
        this.snackBar.open('Dependente removido com sucesso.', '', { duration: 4000 });
      }, () => {
        this.snackBar.open('Erro ao remover dependente.', '', { duration: 4000 });
      });
    }
  }

  getDependentesFormArray() {
    return (<UntypedFormArray>this.form.get('dependentes')).controls;
  }

  private retrieveDependente(socio: Socio) {
    const dependentes = [];
    if (socio?.dependentes) {
      socio.dependentes.forEach(dependente => dependentes.push(this.createDependente(dependente)));
    } else {
      dependentes.push(this.createDependente());
    }
    return dependentes;
  }

  private createDependente(dependente: Dependente = { id: null, nome: '', idade: null }) {
    return this.formBuilder.group({
      id: [dependente.id],
      nome: [dependente.nome, [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(50)
      ]],
      idade: [dependente.idade, [
        Validators.required,
        Validators.min(0)
      ]]
    })
  }

  onSubmit() {
    const formValue = this.form.value;

    this.socioService.save(formValue).subscribe(result => {
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

  getErrorMessage(fieldName: string) {
    const field = this.form.get(fieldName);

    if (field?.hasError('required')) {
      return 'Campo obrigatório';
    }

    if (field?.hasError('min')) {
      return 'Valor deve ser maior ou igual a zero.';
    }

    if (field?.hasError('minlength')) {
      const requiredLength: number = field.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `Tamanho mínimo precisa ser de ${requiredLength} caracteres.`;
    }

    if (field?.hasError('maxlength')) {
      const requiredLength: number = field.errors ? field.errors['maxlength']['requiredLength'] : 50;
      return `Tamanho máximo excedido de ${requiredLength} caracteres.`;
    }

    return 'Campo Inválido';
  }
}
