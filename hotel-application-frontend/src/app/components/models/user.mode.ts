export interface User {
    id: number,
    email: string,
    name: string
}

export interface LoginDTO {
    email: string,
    password: string
}

export interface RegisterDTO {
    name: string,
    email: string,
    password: string
}