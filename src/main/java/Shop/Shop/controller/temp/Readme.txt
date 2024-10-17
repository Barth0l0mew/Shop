**** не работал с формой в admin/index
//    @GetMapping("/editUser")
//    @ResponseBody
//    public String editUser(@RequestParam(name = "id") Long id, Model model) {
//        System.out.println(id);
//        User user = myUserService.findById(id);
//        System.out.println(user.toString());
//        model.addAttribute("editUser", user);
//        // model.addAttribute("users", myUserService.findAll());
//        return "admin/index.html"; // имя вашего HTML-шаблона
//    }

***index.html
<form th:action="@{/admin/editUser(id=${user.id})}" method="post">
                            <button class="btn btn-primary" type="submit">Править</button>
                        </form>