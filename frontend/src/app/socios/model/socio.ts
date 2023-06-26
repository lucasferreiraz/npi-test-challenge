import { Dependente } from "./dependente";

export interface Socio {
  id: number | string,
  nome: string,
  renda: number | string,
  ativo: boolean | string,
  dependentes?: Dependente[]
}
