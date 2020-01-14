import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserControlPageComponent } from './user-control-page.component';

describe('UserControlPageComponent', () => {
  let component: UserControlPageComponent;
  let fixture: ComponentFixture<UserControlPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserControlPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserControlPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
