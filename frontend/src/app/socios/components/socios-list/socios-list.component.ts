import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Socio } from '../../model/socio';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-socios-list',
  templateUrl: './socios-list.component.html',
  styleUrls: ['./socios-list.component.scss']
})
export class SociosListComponent {

  @Input() socios: Socio[] = []
  @Output() add = new EventEmitter(false)

  displayedColumns = ["id", "nome", "renda", "ativo", "actions"]

  constructor() {
  }

  onAdd() {
    this.add.emit(true)
  }
}
