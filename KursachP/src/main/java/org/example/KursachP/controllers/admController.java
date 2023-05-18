package org.example.KursachP.controllers;

import org.example.KursachP.models.*;
import org.example.KursachP.security.EmployeeDetails;
import org.example.KursachP.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class admController  {

    private final AdminService adminService;
    private final ProductService productService;
    private final ShopService shopService;
    private final EmployeeService employeeService;
    private final OrdService orderService;
    private final ClientService clientService;

    private final SheetOrdService sheetOrdService;
    @Autowired
    public admController(ProductService productService, ShopService shopService,
                         EmployeeService employeeService,
                         OrdService orderService, AdminService adminService,
                         ClientService clientService,
                         SheetOrdService sheetOrdService) {
        this.productService = productService;
        this.shopService = shopService;
        this.employeeService = employeeService;
        this.adminService = adminService;
        this.orderService = orderService;
        this.clientService = clientService;
        this.sheetOrdService = sheetOrdService;
    }

    @GetMapping("/main") // Отображает товары, магазины, сотрудников
    public String menu(){
        return "/systemMenu";
    }

    //---------------- 1/3 Отображает товары и магазины вкладка просм
    @GetMapping("/view") // Отображает товары, магазины,  заказы
    public String view(){
        return "/dataView";
    }



    /// -- отображает функционал продавца кассира
    @GetMapping("/view/products") // Отображает товаp
    public String products(Model model){
            model.addAttribute("products", productService.findAll());
            return "/dataViews/products";

    }

    @PostMapping("/view/products") // Отображает товар по поиску
    public String productsSearch(Model model, @RequestParam("search") String search){
        model.addAttribute("productsSearch", productService.findByProductName(search));
        return "/dataViews/productsSearch";
    }


    @GetMapping("/view/shops") // Отображает магазины
    public String shops(Model model)  {

        model.addAttribute("shops", shopService.findAll());
        return "/dataViews/shops";
    }

    @PostMapping("/view/shops") // Отображает магазин по поиску
    public String shopsSearch(Model model, @RequestParam("search") String search){
        model.addAttribute("shopsSearch", shopService.findByNameShop(search));
        return "/dataViews/shopsSearch";
    }

    @GetMapping("/view/ords") // Отображает заказы
    public String orders(Model model){
        model.addAttribute("ords", orderService.findAll());
        return "/dataViews/ords";
    }
    @PostMapping("/view/ords") // Отображает заказ по поиску
    public String ordersSearch (Model model, @RequestParam("search") int search){
        model.addAttribute("ordsSearch", orderService.findByNumberOrder(search));
        return "/dataViews/ordsSearch";
    }
    /// -- отображает функционал продавца кассира

    /// ---

    @GetMapping("/view/employees") // Отображает сотрудников
    public String employees(Model model){
        adminService.doAccountantAdminDirector();
        model.addAttribute("employees", employeeService.findAll());
        return "/dataViews/employees";
    }

    @PostMapping("/view/employees") // Отображает сотрудника по поиску
    public String employeesSearch(Model model, @RequestParam("search") String search){
        adminService.doAccountantAdminDirector();
        model.addAttribute("employeesSearch", employeeService.findByEmployeeName(search));
        return "/dataViews/employeesSearch";
    }

    /// 2/4
    @GetMapping("/edit")
    public String edit(){
        adminService.doAdminDirector();
        return "dataEdit";
    }

/// - редактирование товаров

    @GetMapping("/edit/products") //страница со списком товаров и добавлением
    public String Products(Model model, @ModelAttribute("newProduct") Product newProduct){
        adminService.doAdminDirector();
        model.addAttribute("products", productService.findAll());
        return "dataEdit/products";
    }
    @PostMapping("/edit/newProduct") //товар добавлен
    public String productAdded(@ModelAttribute("newProduct") Product newProduct){
        adminService.doAdminDirector();
        productService.save(newProduct);
       return "dataEdit/newProduct";
    }

    @GetMapping("/edit/products/{id}") //переход на вкладку товара для ред и уд
    public String editProduct(@PathVariable("id") int id, Model model){
        adminService.doAdminDirector();
        model.addAttribute("product", productService.findOne(id));
        return "dataEdit/productsEdit";
    }

    @PatchMapping("/edit/products/{id}") //отправляется отред товар
    public String editProduct(@PathVariable("id") int id,
            @ModelAttribute("product") Product product){
        adminService.doAdminDirector();
        productService.update(id, product);
        return "redirect:/edit/products";
    }

    @DeleteMapping("/edit/products/{id}") //запрос на удаление
    public String deleteProduct(@PathVariable("id") int id){
        adminService.doAdminDirector();
        productService.delete(id);
        return "redirect:/edit/products";
    }

    // - редактирование магазинов

    @GetMapping("/edit/shops")
    public String Shops(@ModelAttribute ("newShop") Shop newShop,
                            Model model){
        adminService.doDirector();
        model.addAttribute("shops", shopService.findAll());
        return "dataEdit/shops";
    }

    @PostMapping("/edit/newShop")
    public String ShopsAdded(@ModelAttribute ("newShop") Shop newShop){
        adminService.doDirector();
        shopService.save(newShop);
        return "dataEdit/newShop";
    }

    @GetMapping("/edit/shops/{id}")
    public String editShops(@PathVariable("id") int id, Model model){
        adminService.doDirector();
        model.addAttribute("shop", shopService.findOne(id));
        return "dataEdit/shopEdit";
    }

    @PatchMapping("/edit/shops/{id}")
    public String editShop(@PathVariable("id") int id,
            @ModelAttribute ("shop") Shop shop){
        adminService.doDirector();
        shopService.update(id, shop);
        return "redirect:/edit/shops";
    }
    @DeleteMapping("/edit/shops/{id}")
    public String deleteShop(@PathVariable("id") int id){
        adminService.doDirector();
        shopService.delete(id);
        return "redirect:/edit/shops";
    }


    @GetMapping("/edit/employees")
    public String employee(@ModelAttribute ("newEmployee") Employee employee,
                           Model model){

        model.addAttribute("employees", employeeService.findAll());
        adminService.doAdminDirector();
        return "/dataEdit/employees";
    }

    @PostMapping("/edit/employees")
    public String employee(@ModelAttribute ("newEmployee") Employee employee){

        employeeService.save(employee);
        adminService.doAdminDirector();
        return "redirect:/edit/employees";
    }

    @GetMapping("/edit/employees/{id}")
    public String employee(@PathVariable ("id") int id, Model model){
        model.addAttribute("employee", employeeService.findOne(id));
        adminService.doAdminDirector();
        return "/dataEdit/employeeEdit";
    }
    @PatchMapping("/edit/employees/{id}")
    public String employee(@PathVariable ("id") int id,
                           @ModelAttribute ("employee") Employee employee){
        employeeService.update(id, employee);
        adminService.doAdminDirector();
        return "redirect:/edit/employees";
    }

    @DeleteMapping("/edit/employees/{id}")
    public String employee(@PathVariable ("id") int id){
        employeeService.delete(id);
        adminService.doAdminDirector();
        return "redirect:/edit/employees";
    }


// Order
    @GetMapping("/order")
    public String order(){
        return "/dataOrder";
    }

    @GetMapping("/order/create")
    public String order(@ModelAttribute ("sheetOrder") SheetOrd sheetOrd,
                        @ModelAttribute ("product") Product product,
                        Model model){
        model.addAttribute("products", productService.findAll());
        model.addAttribute("sheetOrds", sheetOrdService.findAll());
        return "/order/create";
    }
    @PostMapping("/order/create")
    public String order(@ModelAttribute ("sheetOrder") SheetOrd sheetOrd,
                        @ModelAttribute ("product") Product product){
        productService.updateAmount(product.getId(), product.getAmount());
        sheetOrd = productService.setSheetOrd(sheetOrd, product);
        sheetOrdService.save(sheetOrd);

        return "redirect:/order/create";
    }

    @GetMapping("/order/create/design")
    public String order(@ModelAttribute ("client") Client client){

        return "/order/design";
    }

    @PostMapping("/order/create/design")
    public String orderDesign(@ModelAttribute ("client") Client client){
        clientService.save(client);

        List<SheetOrd> sheetOrds = sheetOrdService.findAll();
        Ord ord = new Ord();
        for(int i = 0; i < sheetOrds.size(); i++){
            ord.setId(sheetOrds.get(i).getId());
            ord.setIdProduct(sheetOrds.get(i).getIdProduct());
            ord.setNameProduct(sheetOrds.get(i).getNameProduct());
            ord.setAmount(sheetOrds.get(i).getAmount());
            ord.setNumberOrder(client.getId());
            ord.setPrice(productService.findOne(sheetOrds.get(i).getIdProduct()).getPrice());
            ord.setPriceSum(ord.getAmount()*productService.findOne(sheetOrds.get(i).getIdProduct()).getPrice());
            sheetOrdService.delete(sheetOrds.get(i).getId());
            orderService.save(ord);
        }

        return "redirect:/order/create";
    }


    @GetMapping("/order/create/{id}")
    public String order(@ModelAttribute ("sheetOrder") SheetOrd sheetOrd){
        return "/order/delete";
    }

    @DeleteMapping ("/order/create/{id}")
    public String order(@PathVariable("id") int id){
        SheetOrd sheetOrd = sheetOrdService.findOne(id);

        productService.updateAmountPlus(id, sheetOrd.getAmount());
        sheetOrdService.delete(id);
        return "redirect:/order/create";
    }





}
