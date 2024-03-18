export default function status(id: number){
    switch(id) {
        case 1: return "created";
        case 3: return "closed";
        default: return "open";
    }
}